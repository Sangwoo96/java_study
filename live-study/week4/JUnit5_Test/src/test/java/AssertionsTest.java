import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssertionsTest {
    private final Calculator calculator = new Calculator();

    //기본
    @Test
    void standardAssertions(){
        assertEquals(2, calculator.add(1,1));
        assertEquals(4, calculator.multiply(2,2), "실패 메세지1");

        assertTrue('a' < 'b', "실패 메세지2");
    }

    //그룹
    @Test
    void groupedAssertions(){
        assertAll(
                ()->assertEquals(2, calculator.add(1,1)),
                ()->assertEquals(4, calculator.multiply(2,2))
        );
    }

    //의존
    @Test
    void dependentAssertions(){
        assertAll(
                ()->{
                    assertEquals(2, calculator.add(1,1));
                },
                ()->{
                    assertEquals(4, calculator.multiply(2,2));
                }
        );
    }

    //예외
    @Test
    void exception(){
        Exception e = assertThrows(ArithmeticException.class, () -> calculator.div(1, 0));
        assertEquals("/ by zero", e.getMessage());
    }

    //제한시간
    @Test
    void timeoutNotExceeded() {
        assertTimeout(ofMinutes(2), () -> {
            // 2분 미만의 로직만 통과
        });
    }
}