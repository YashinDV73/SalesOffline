package ru.sales.offline.utilites;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sales.offline.SalesOfflineApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public final class AppUtils {
  private static final Logger logger = LoggerFactory.getLogger(SalesOfflineApplication.class);
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static String getPropertiesValue(Properties properties, String propertiesKey) {
    String paramValue =
        new String(
            properties.getProperty(propertiesKey).getBytes(StandardCharsets.ISO_8859_1),
            StandardCharsets.UTF_8);
    logger.info("Получено значение параметра {}: {}", propertiesKey, paramValue);
    return paramValue;
  }

  public static byte[] getObjectAsJson(Object object) {
    try {
      byte[] bytes = OBJECT_MAPPER.writeValueAsString(object).getBytes(StandardCharsets.UTF_8);
      logger.info(new String(bytes, StandardCharsets.UTF_8));
      return bytes;
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }
}
