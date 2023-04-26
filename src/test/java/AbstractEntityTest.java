import com.example.application.data.entity.AbstractEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AbstractEntityTest {

    private TestEntity entity1;
    private TestEntity entity2;

    @BeforeEach
    public void setUp() {
        entity1 = new TestEntity();
        entity2 = new TestEntity();

        entity1.setId(1L);
        entity2.setId(2L);
    }

    @Test
    public void testIdAndVersion() {
        assertEquals(1L, entity1.getId());
        assertEquals(2L, entity2.getId());
        assertEquals(0, entity1.getVersion());
        assertEquals(0, entity2.getVersion());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertNotEquals(entity1, entity2);
        assertNotEquals(entity1.hashCode(), entity2.hashCode());

        entity2.setId(entity1.getId());
        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Entity
    static class TestEntity extends AbstractEntity {
    }
}

