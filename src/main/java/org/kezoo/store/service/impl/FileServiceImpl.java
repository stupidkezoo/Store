package org.kezoo.store.service.impl;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.codehaus.jackson.map.ObjectMapper;
import org.kezoo.store.utils.FileUtils;
import org.kezoo.store.dao.ProductDao;
import org.kezoo.store.model.Product;
import org.kezoo.store.service.DaoManager;
import org.kezoo.store.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

import static org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL;

@Service
public class FileServiceImpl implements FileService{

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    private static final String TYPE = "type";
    private static final String QUANTITY = "quantity";
    private static final String PARAMETERS = "parameters";

    private ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(NON_NULL);

    @Autowired
    private DaoManager daoManager;

    @Override
    public void exportToFile(String path) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(objectMapper.writeValueAsString(getAll()));
            log.info("Successfully exported records to file {}", path);
        } catch (Exception e) {
            log.error("Failed to export records", e);
        }
    }

    private Map<String, List<Product>> getAll() {
        return daoManager.getAllDao().stream()
                .collect(Collectors.<ProductDao, String, List<Product>>
                        toMap(e -> e.getClazz().getSimpleName(), ProductDao::getAll));
    }

    @Override
    @Transactional
    public void importFromFile(String path) {
        try (Reader in = new FileReader(path)){
            Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader(TYPE, QUANTITY, PARAMETERS).parse(in);
            for (CSVRecord record : records) {
                importRecord(record);
            }
            log.info("Successfully imported records from file {}", path);
        } catch (Exception e) {
            log.error("Failed to import records", e);
        }
    }

    private void importRecord(CSVRecord record) {
        Long quantity = Long.valueOf(record.get(QUANTITY));
        Map<String, Object> parameters = parseParametersMap(record);
        ProductDao productDao = getProductDao(record);
        Product product = productDao.getById((String)parameters.get("serial"));
        if (product != null) {
            incrementQuantity(quantity, productDao, product);
        } else {
            saveNewProduct(quantity, parameters, productDao);
        }
    }

    private Map<String, Object> parseParametersMap(CSVRecord record) {
        return Arrays.stream(record.get(PARAMETERS).replace("[","").replace("]","").split(",")).map(s -> s.split(":")).collect(Collectors.toMap(e -> e[0], e -> e[1]));
    }

    private ProductDao getProductDao(CSVRecord record) {
        String type = record.get(TYPE);
        Class clazz = FileUtils.getClass(type);
        return daoManager.get(clazz);
    }

    private void incrementQuantity(Long quantity, ProductDao productDao, Product product) {
        product.setQuantity(product.getQuantity() + quantity);
        productDao.update(product);
    }

    private void saveNewProduct(Long quantity, Map<String, Object> parameters, ProductDao productDao) {
        Product newProduct = (Product) objectMapper.convertValue(parameters, productDao.getClazz());
        newProduct.setQuantity(quantity);
        productDao.save(newProduct);
    }



}


