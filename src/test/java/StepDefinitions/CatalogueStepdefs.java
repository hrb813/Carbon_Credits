package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static utility.JAXRS.*;
import static utility.JAXRS.getJSONObject;
import static utility.JAXRS.getKeyValues;
import static utility.JAXRS.getObjectValues;

public class CatalogueStepdefs {


    @And("^The \"([^\"]*)\" field should have the value \"([^\"]*)\"$")
    public void theFieldShouldHaveTheValue(String arg0, String arg1) throws Throwable {
        Assert.assertEquals(arg0 + " field expects the value " +arg1,arg1,getKeyValues(arg0));
    }


    @When("^The parameters are \"([^\"]*)\" and the value is \"([^\"]*)\"$")
    public void theParametersAreAndTheValueIs(String arg0, String arg1) throws Throwable {
        xparameter = arg0;
        xvalue = arg1;

    }

/** Use this for hard coded URL**/
    @Given("^the API URL is \"([^\"]*)\"$")
    public void theAPIURLIs(String arg0) throws Throwable {
        host = arg0;
    }

/** Use this for flexibility of REST Applications **/
    @Given("^the API URL is \"([^\"]*)\" and the application \"([^\"]*)\"$")
    public void theAPIURLIsAndTheApplication(String arg0, String arg1) throws Throwable {
        host = arg0;
        app = arg1;
    }

    @And("^The \"([^\"]*)\" object that has the field key \"([^\"]*)\" with the value \"([^\"]*)\" and the field \"([^\"]*)\" should contain \"([^\"]*)\"$")
    public void theObjectThatHasTheFieldKeyWithTheValueAndTheFieldShouldContain(String arg0, String arg1, String arg2, String arg3, String arg4) throws Throwable {
        try {
            Boolean doesContain = Boolean.FALSE;
            if(getObjectValues(arg0,arg1,arg2,arg3).contains(arg4)){
                doesContain = Boolean.TRUE;
            }

            Assert.assertTrue("Value Found", doesContain);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @And("^the path is \"([^\"]*)\"$")
    public void thePathIs(String arg0) throws Throwable {
        app = arg0;
    }

    @And("^The response is mapped$")
    public void theResponseIsMapped() throws Throwable {
        getJSONObject(app,xparameter,xvalue);
    }
}
