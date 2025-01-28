package com.sqldomaingen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TypeMapper {

    private static final Logger logger = LoggerFactory.getLogger(TypeMapper.class);

    // Σταθερές για Java Types
    private static final String JAVA_BIG_DECIMAL = "java.math.BigDecimal";
    private static final String JAVA_STRING = "String";
    private static final String JAVA_LOCAL_DATE_TIME = "java.time.LocalDateTime";

    private static final Map<String, String> sqlToJavaTypeMap = new HashMap<>();

    // Ιδιωτικός constructor για να αποτρέψουμε τη δημιουργία αντικειμένων.
    public TypeMapper() {
        // Πετάμε εξαίρεση για να καταστήσουμε σαφές ότι η κλάση δεν επιτρέπεται να δημιουργηθεί.
        throw new AssertionError("Utility class - instantiation not allowed.");
    }



    static {
        sqlToJavaTypeMap.put("BIGINT", "Long");
        sqlToJavaTypeMap.put("LONG", "Long");
        sqlToJavaTypeMap.put("INT", "Long");
        sqlToJavaTypeMap.put("INTEGER", "Long");
        sqlToJavaTypeMap.put("SMALLINT", "Short");
        sqlToJavaTypeMap.put("TINYINT", "Byte");

        sqlToJavaTypeMap.put("DECIMAL", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("NUMERIC", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("FLOAT", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("DOUBLE", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("DOUBLE PRECISION", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("REAL", "Double");

        sqlToJavaTypeMap.put("CHAR", JAVA_STRING);
        sqlToJavaTypeMap.put("VARCHAR", JAVA_STRING);
        sqlToJavaTypeMap.put("TEXT", JAVA_STRING);
        sqlToJavaTypeMap.put("JSON", JAVA_STRING);
        sqlToJavaTypeMap.put("JSONB", JAVA_STRING);

        sqlToJavaTypeMap.put("DATE", "java.time.LocalDate");
        sqlToJavaTypeMap.put("TIME", "java.time.LocalTime");
        sqlToJavaTypeMap.put("TIME WITHOUT TIME ZONE", "java.time.LocalTime");
        sqlToJavaTypeMap.put("TIME WITH TIME ZONE", "java.time.OffsetTime");
        sqlToJavaTypeMap.put("TIMESTAMP", JAVA_LOCAL_DATE_TIME);
        sqlToJavaTypeMap.put("TIMESTAMP WITHOUT TIME ZONE", JAVA_LOCAL_DATE_TIME);
        sqlToJavaTypeMap.put("TIMESTAMP WITH TIME ZONE", "java.time.OffsetDateTime");
        sqlToJavaTypeMap.put("DATETIME", JAVA_LOCAL_DATE_TIME);

        sqlToJavaTypeMap.put("BLOB", "byte[]");
        sqlToJavaTypeMap.put("BYTEA", "byte[]");

        sqlToJavaTypeMap.put("BOOLEAN", "Boolean");
        sqlToJavaTypeMap.put("BIT", "Boolean");

        sqlToJavaTypeMap.put("UUID", "java.util.UUID");
        sqlToJavaTypeMap.put("ARRAY", "java.util.List<?>");
        sqlToJavaTypeMap.put("MONEY", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("SERIAL", "Long");
        sqlToJavaTypeMap.put("BIGSERIAL", "Long");
        sqlToJavaTypeMap.put("SMALLSERIAL", "Short");
        sqlToJavaTypeMap.put("ENUM", JAVA_STRING);
        sqlToJavaTypeMap.put("CITEXT", JAVA_STRING); // Κείμενο χωρίς διάκριση πεζών/κεφαλαίων
        sqlToJavaTypeMap.put("TSVECTOR", JAVA_STRING); // Full-text search
        sqlToJavaTypeMap.put("INET", JAVA_STRING); // Διεύθυνση IP
        sqlToJavaTypeMap.put("CIDR", JAVA_STRING); // Διεύθυνση IP
        sqlToJavaTypeMap.put("MACADDR", JAVA_STRING); // Διεύθυνση MAC
        sqlToJavaTypeMap.put("XML", JAVA_STRING); // XML δεδομένα
        sqlToJavaTypeMap.put("PG_LSN", JAVA_STRING); // Log sequence number
        sqlToJavaTypeMap.put("VARBIT", JAVA_STRING); // Δυαδική τιμή μεταβλητού μήκους
        sqlToJavaTypeMap.put("TRIGGER", JAVA_STRING); // Placeholder για triggers
        sqlToJavaTypeMap.put("INTERVAL", JAVA_STRING);
    }

    public static String mapToJavaType(String sqlType) {
        if (sqlType == null || sqlType.isEmpty()) {
            logger.error("SQL type cannot be null or empty");
            throw new IllegalArgumentException("SQL type cannot be null or empty");
        }

        String baseType = sqlType.split("\\(")[0].toUpperCase();
        String javaType = sqlToJavaTypeMap.getOrDefault(baseType, JAVA_STRING);

        if (!sqlToJavaTypeMap.containsKey(baseType)) {
            // Καταγραφή προειδοποίησης μόνο αν το baseType δεν υπάρχει στο mapping
            logger.warn("No specific mapping found for SQL type '{}'. Defaulting to 'String'.", baseType);
        } else {
            logger.info("Mapping SQL type '{}' to Java type '{}'", baseType, javaType);
        }

        return javaType;
    }

}
