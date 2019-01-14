
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;



    @RunWith(Cucumber.class)
    @CucumberOptions(
//            strict=true,
//            format = {"pretty", "html:target/htmlreports"},
            features = {"src/test/resources/features"},
//            glue = {"src/test/StepDefinitions"},
            plugin = {"pretty"},
            tags = {"@regression,@test,@RunThisTest"})

    public final class TestRunner {


    }

