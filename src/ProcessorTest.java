import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {

    @org.junit.jupiter.api.Test
    void near5Percent() {
        assertTrue(Math.abs((Processor.near5Percent(1.03)) - 1.05) < 0.0001);
    }

    @org.junit.jupiter.api.Test
    void roundPrice() {
        assertTrue(Math.abs((Processor.roundPrice(10.125456) - 10.12)) > 0.008);
    }

    @org.junit.jupiter.api.Test
    void isExempt() {
        assertTrue(Processor.isExempt("music CD"));
    }
}