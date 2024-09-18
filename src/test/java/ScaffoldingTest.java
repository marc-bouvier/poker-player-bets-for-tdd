import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.leanpoker.player.Player.betRequest;

public class ScaffoldingTest {


    // Null case
    @Test
    void betRequest_always_zero_or_greater(
    ) {
        assertThat(betRequest(null)).isGreaterThanOrEqualTo(0);
    }

    // What is in the request


}
