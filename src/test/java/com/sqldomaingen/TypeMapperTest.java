package com.sqldomaingen;

import com.sqldomaingen.util.TypeMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TypeMapperTest {

    @Test
    void testPostgreSqlDataTypes() {
        // Ακέραιοι τύποι
        assertEquals("Long", TypeMapper.mapToJavaType("Integer"));
        assertEquals("Long", TypeMapper.mapToJavaType("INT"));
        assertEquals("Short", TypeMapper.mapToJavaType("SMALLINT"));
        assertEquals("Long", TypeMapper.mapToJavaType("BIGINT"));
        assertEquals("Long", TypeMapper.mapToJavaType("BIGSERIAL"));
        assertEquals("Long", TypeMapper.mapToJavaType("SERIAL"));
        assertEquals("Short", TypeMapper.mapToJavaType("SMALLSERIAL"));

        // Τύποι κινητής υποδιαστολής
        assertEquals("Double", TypeMapper.mapToJavaType("REAL"));
        assertEquals("java.math.BigDecimal", TypeMapper.mapToJavaType("DOUBLE PRECISION"));
        assertEquals("java.math.BigDecimal", TypeMapper.mapToJavaType("NUMERIC"));
        assertEquals("java.math.BigDecimal", TypeMapper.mapToJavaType("DECIMAL"));
        assertEquals("java.math.BigDecimal", TypeMapper.mapToJavaType("NUMERIC(10,2)"));
        assertEquals("java.math.BigDecimal", TypeMapper.mapToJavaType("DECIMAL(15,5)"));
        assertEquals("java.math.BigDecimal", TypeMapper.mapToJavaType("MONEY"));

        // Αλφαριθμητικά
        assertEquals("String", TypeMapper.mapToJavaType("CHAR(10)"));
        assertEquals("String", TypeMapper.mapToJavaType("VARCHAR(255)"));
        assertEquals("String", TypeMapper.mapToJavaType("TEXT"));
        assertEquals("String", TypeMapper.mapToJavaType("CITEXT"));

        // Λογικές τιμές
        assertEquals("Boolean", TypeMapper.mapToJavaType("BOOLEAN"));

        // JSON δεδομένα
        assertEquals("String", TypeMapper.mapToJavaType("JSON"));
        assertEquals("String", TypeMapper.mapToJavaType("JSONB"));

        // Ημερομηνίες και Ώρες
        assertEquals("java.time.LocalDate", TypeMapper.mapToJavaType("DATE"));
        assertEquals("java.time.LocalTime", TypeMapper.mapToJavaType("TIME"));
        assertEquals("java.time.LocalTime", TypeMapper.mapToJavaType("TIME WITHOUT TIME ZONE"));
        assertEquals("java.time.OffsetTime", TypeMapper.mapToJavaType("TIME WITH TIME ZONE"));
        assertEquals("java.time.LocalDateTime", TypeMapper.mapToJavaType("TIMESTAMP"));
        assertEquals("java.time.OffsetDateTime", TypeMapper.mapToJavaType("TIMESTAMP WITH TIME ZONE"));

        // Ειδικοί τύποι χρόνου
        assertEquals("String", TypeMapper.mapToJavaType("INTERVAL"));

        // Μοναδικοί τύποι και πίνακες
        assertEquals("java.util.UUID", TypeMapper.mapToJavaType("UUID"));
        assertEquals("java.util.List<?>", TypeMapper.mapToJavaType("ARRAY"));

        // Δυαδικά δεδομένα
        assertEquals("byte[]", TypeMapper.mapToJavaType("BYTEA"));

        // ENUM τύποι
        assertEquals("String", TypeMapper.mapToJavaType("ENUM('value1', 'value2')"));

        // Άλλοι ειδικοί τύποι
        assertEquals("String", TypeMapper.mapToJavaType("TSVECTOR"));
        assertEquals("String", TypeMapper.mapToJavaType("INET"));
        assertEquals("String", TypeMapper.mapToJavaType("CIDR"));
        assertEquals("String", TypeMapper.mapToJavaType("MACADDR"));
        assertEquals("String", TypeMapper.mapToJavaType("XML"));
        assertEquals("String", TypeMapper.mapToJavaType("PG_LSN"));
        assertEquals("Boolean", TypeMapper.mapToJavaType("BIT(1)"));
        assertEquals("String", TypeMapper.mapToJavaType("VARBIT(100)"));

        // Άγνωστος τύπος
        assertEquals("String", TypeMapper.mapToJavaType("TRIGGER"));
        assertEquals("String", TypeMapper.mapToJavaType("UNKNOWN_TYPE"));
    }
}
