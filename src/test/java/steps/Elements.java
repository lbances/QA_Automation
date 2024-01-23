package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Elements {
    private final WebDriver driver = WebDriverConfig.getDriver();

    @Given("me encuentro en la página Demo QA Elements")
    public void openBrowser_DemoQA(){
        driver.get("https://demoqa.com/elements");
    }

    // Inicio Text Box
    @When("selecciono la opción Text Box")
    public void selectTextBox(){
        WebElement btnTextBox = driver.findElement(By.id("item-0"));
        btnTextBox.click();
    }

    @Then("debo completar los campos y dar clic en el botón Submit")
    public void completeData() {
        WebElement txtNombre = driver.findElement(By.id("userName"));
        WebElement txtEmail = driver.findElement(By.id("userEmail"));
        WebElement txtDireccion = driver.findElement(By.id("currentAddress"));
        WebElement txtDireccion2 = driver.findElement(By.id("permanentAddress"));
        WebElement btnSubmit = driver.findElement(By.id("submit"));

        txtNombre.sendKeys("Leonardo Automation");
        txtEmail.sendKeys("leo_automation@mailinator.com");
        txtDireccion.sendKeys("Dirección actual");
        txtDireccion2.sendKeys("Dirección permanente");

        // Realiza scroll al botón para asegurarte de que esté visible
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", btnSubmit);

        // Espera a que el botón sea cliclable y haz clic
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement clickableBtnSubmit = wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
        clickableBtnSubmit.click();
    }

    @But("los datos enviamos deben ser los mismos que muestran como resultado")
    public void validateData(){
        validarDatos();
    }

    @SuppressWarnings("ConstantConditions")
    private void validarDatos(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement outputDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));

        WebElement nameText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement emailText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

        String actualName = nameText.getText();
        String expectedName = "Name:" + "Leonardo Automation"; // Actualizar el dato si no son iguales

        String actualEmail = emailText.getText();
        String expectedEmail = "Email:" + "leo_automation@mailinator.com"; // Actualizar el dato si no son iguales

        WebElement currentAddressElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='currentAddress']")));
        String actualCurrentAddress = currentAddressElement.getText().trim();
        String expectedCurrentAddress = "Current Address :" + "Dirección actual"; // Actualizar el dato si no son iguales

        WebElement permanentAddressElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='permanentAddress']")));
        String actualPermanentAddress = permanentAddressElement.getText().trim();
        String expectedPermanentAddress = "Permananet Address :" + "Dirección permanente"; // Actualizar el dato si no son iguales

        if (expectedName.equals(actualName) && expectedEmail.equals(actualEmail) && expectedCurrentAddress.equals(actualCurrentAddress) && expectedPermanentAddress.equals(actualPermanentAddress)) {
            System.out.println("  Los datos coinciden correctamente.");
        } else if (!expectedName.equals(actualName)) {
            System.out.println("  Los datos del nombre no coinciden, revisar.");
        } else if (!expectedEmail.equals(actualEmail)) {
            System.out.println("  Los datos del correo no coinciden, revisar.");
        } else if (!expectedCurrentAddress.equals(actualCurrentAddress)) {
            System.out.println("  Los datos de la dirección actual no coinciden, revisar.");
        } else if (!expectedPermanentAddress.equals(actualPermanentAddress)) {
            System.out.println("  Los datos de la dirección permanente no coinciden, revisar.");
        }
    }
    // Fin Text Box

    // Inicio Check Box - Caso 1
    @When("selecciono la opción Check Box")
    public void selectCheckBox(){
        WebElement btnCheckBox = driver.findElement(By.id("item-1"));
        btnCheckBox.click();
    }

    @Then("debo darle clic en el checkbox Home")
    public void selectCheckBoxHome(){
        // JavaScript Executor para obtener y hacer clic en el elemento
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement cbHome = (WebElement) executor.executeScript("return document.getElementById('tree-node-home');");
        executor.executeScript("arguments[0].click();", cbHome);
    }

    @And("mostrará todas las carpetas existentes")
    public void validatePackageHome(){
        // Espera a que aparezca el mensaje después de hacer clic en el checkbox
        WebElement resultDiv = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));

        // Recupera el texto del mensaje
        String actualMessage = resultDiv.getText();

        // Cadena esperada con saltos de línea y espacios adicionales
        String expectedMessage = "You have selected :\nhome\ndesktop\nnotes\ncommands\ndocuments\nworkspace\nreact\nangular\nveu\noffice\npublic\nprivate\nclassified\ngeneral\ndownloads\nwordFile\nexcelFile";

        // Compara el texto actual con la cadena esperada
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("  El mensaje mostrado es el esperado.");
        } else {
            System.out.println("  El mensaje mostrado no es el esperado. Mensaje actual: " + actualMessage);
        }
    }
    // Fin Check Box - Caso 1

    // Inicio Check Box - Caso 2
    @And("actualizo la página")
    public void refreshPage(){
        driver.navigate().refresh();
    }

    @Then("debo dar clic en el botón desplegable de Home")
    public void selectBtnDropdownHome(){
        WebElement btnHome = driver.findElement(By.xpath("//button[@aria-label='Toggle']"));
        btnHome.click();
    }

    @And("seleccionaré el checkbox Desktop")
    public void selectCheckboxDesktop(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement cbDesktop = (WebElement) executor.executeScript("return document.getElementById('tree-node-desktop');");
        executor.executeScript("arguments[0].click();", cbDesktop);
    }

    @But("pero validaré que se muestre las carpetas solamente de Desktop")
    public void validatePackageDesktop(){
        // Espera a que aparezca el mensaje después de hacer clic en el checkbox
        WebElement resultDiv = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));

        // Recupera el texto del mensaje
        String actualMessage = resultDiv.getText();

        // Cadena esperada con saltos de línea y espacios adicionales
        String expectedMessage = "You have selected :\ndesktop\nnotes\ncommands";

        // Compara el texto actual con la cadena esperada
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("  El mensaje mostrado es el esperado.");
        } else {
            System.out.println("  El mensaje mostrado no es el esperado. Mensaje actual: " + actualMessage);
        }
    }
    // Fin Check Box - Caso 2

    // Inicio Check Box - Caso 3
    @And("seleccionaré el checkbox Documents")
    public void selectCheckboxDocuments(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement cbDocuments = (WebElement) executor.executeScript("return document.getElementById('tree-node-documents');");
        executor.executeScript("arguments[0].click();", cbDocuments);
    }

    @But("pero validaré que se muestre las carpetas solamente de Documents")
    public void validatePackageDocuments(){
        // Espera a que aparezca el mensaje después de hacer clic en el checkbox
        WebElement resultDiv = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));

        // Recupera el texto del mensaje
        String actualMessage = resultDiv.getText();

        // Cadena esperada con saltos de línea y espacios adicionales
        String expectedMessage = "You have selected :\ndocuments\nworkspace\nreact\nangular\nveu\noffice\npublic\nprivate\nclassified\ngeneral";

        // Compara el texto actual con la cadena esperada
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("  El mensaje mostrado es el esperado.");
        } else {
            System.out.println("  El mensaje mostrado no es el esperado. Mensaje actual: " + actualMessage);
        }
    }
    // Fin Check Box - Caso 3

    // Inicio Check Box - Caso 4
    @And("seleccionaré el checkbox Downloads")
    public void selectCheckboxDownloads(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement cbDownloads = (WebElement) executor.executeScript("return document.getElementById('tree-node-downloads');");
        executor.executeScript("arguments[0].click();", cbDownloads);
    }

    @But("pero validaré que se muestre las carpetas solamente de Downloads")
    public void validatePackageDownloads(){
        // Espera a que aparezca el mensaje después de hacer clic en el checkbox
        WebElement resultDiv = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));

        // Recupera el texto del mensaje
        String actualMessage = resultDiv.getText();

        // Cadena esperada con saltos de línea y espacios adicionales
        String expectedMessage = "You have selected :\ndownloads\nwordFile\nexcelFile";

        // Compara el texto actual con la cadena esperada
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("  El mensaje mostrado es el esperado.");
        } else {
            System.out.println("  El mensaje mostrado no es el esperado. Mensaje actual: " + actualMessage);
        }
    }
    // Fin Check Box - Caso 4

    // Inicio Radio Button - Caso 1
    @When("selecciono la opción Radio Button")
    public void selectRadioButton(){
        WebElement btnRadioButton = driver.findElement(By.id("item-2"));
        btnRadioButton.click();
    }

    @Then("debo seleccionar el radiobutton Yes")
    public void selectRadioButtonYes() {
        WebElement rbYes = driver.findElement(By.id("yesRadio"));

        // Utilizar JavaScript Executor para hacer clic en el radio button
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", rbYes);
    }

    @And("debe mostrarse un mensaje indicando el radiobutton Yes seleccionado")
    public void messageRadioButtonYes() {
        // Espera a que aparezca el mensaje después de hacer clic en el radio button
        WebElement pMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='mt-3']")));

        // Recupera el texto del mensaje
        String actualMessage = pMessage.getText();

        // Cadena esperada con saltos de línea y espacios adicionales
        String expectedMessage = "You have selected Yes";

        // Compara el texto actual con la cadena esperada
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("  El mensaje mostrado es el esperado.");
        } else {
            System.out.println("  El mensaje mostrado no es el esperado. Mensaje actual: " + actualMessage);
        }
    }
    // Fin Radio Button - Caso 1

    // Inicio Radio Button - Caso 2
    @Then("debo seleccionar el radiobutton Impressive")
    public void selectRadioButtonImpressive(){
        WebElement rbImpressive = driver.findElement(By.id("impressiveRadio"));

        // Utilizar JavaScript Executor para hacer clic en el radio button
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", rbImpressive);
    }

    @And("debe mostrarse un mensaje indicando el radiobutton Impressive seleccionado")
    public void messageRadioButtonImpressive(){
        // Espera a que aparezca el mensaje después de hacer clic en el radio button
        WebElement pMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='mt-3']")));

        // Recupera el texto del mensaje
        String actualMessage = pMessage.getText();

        // Cadena esperada con saltos de línea y espacios adicionales
        String expectedMessage = "You have selected Impressive";

        // Compara el texto actual con la cadena esperada
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("  El mensaje mostrado es el esperado.");
        } else {
            System.out.println("  El mensaje mostrado no es el esperado. Mensaje actual: " + actualMessage);
        }
    }
    // Fin Radio Button - Caso 2

    // Inicio Web Tables - Caso 1
    @When("selecciono la opción Web Tables")
    public void selectWebTables(){
        WebElement btnWebTables = driver.findElement(By.id("item-3"));
        btnWebTables.click();
    }

    @Then("debo dar clic en el botón Add")
    public void selectBtnAdd(){
        WebElement btnAdd = driver.findElement(By.id("addNewRecordButton"));
        btnAdd.click();
    }

    @And("debe mostrarse un modal con campos para completar")
    public void showModalRegister(){
        WebElement modal = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));

        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        WebElement userEmailInput = driver.findElement(By.id("userEmail"));
        WebElement ageInput = driver.findElement(By.id("age"));
        WebElement salaryInput = driver.findElement(By.id("salary"));
        WebElement departmentInput = driver.findElement(By.id("department"));

        firstNameInput.sendKeys("Leonardo");
        lastNameInput.sendKeys("Bances");
        userEmailInput.sendKeys("qa_automation_leonardo@mailinator.com");
        ageInput.sendKeys("21");
        salaryInput.sendKeys("50000");
        departmentInput.sendKeys("IT");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
    }

    @But("verificar que los datos que se muestran en la fila de tabla sean los mismos que fueron enviados")
    public void verifyDataTable(){
        WebElement cuartaFila = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='rt-tr-group'])[4]")));

        List<WebElement> celdasCuartaFila = cuartaFila.findElements(By.cssSelector(".rt-td"));

        String expectedFirstName = "Leonardo";
        String expectedLastName = "Bances";
        String expectedUserEmail = "qa_automation_leonardo@mailinator.com";
        String expectedAge = "21";
        String expectedSalary = "50000";
        String expectedDepartment = "IT";

        Assert.assertEquals(expectedFirstName, celdasCuartaFila.get(0).getText());

        Assert.assertEquals(expectedLastName, celdasCuartaFila.get(1).getText());

        Assert.assertEquals(expectedUserEmail, celdasCuartaFila.get(3).getText());

        Assert.assertEquals(expectedAge, celdasCuartaFila.get(2).getText());

        Assert.assertEquals(expectedSalary, celdasCuartaFila.get(4).getText());

        Assert.assertEquals(expectedDepartment, celdasCuartaFila.get(5).getText());
    }
    // Fin Web Tables - Caso 1

    // Inicio Web Tables - Caso 2
    @Then("en el buscador escribo el nombre a buscar")
    public void writeName(){
        WebElement searchData = driver.findElement(By.id("searchBox"));
        searchData.clear();
        searchData.sendKeys("Alden");
    }

    @And("extraigo todos los datos del registro para mostrarlo en consola")
    public void extractDataRow(){
        WebElement cuartaFila = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='rt-tr-group'])[1]")));

        List<WebElement> celdasCuartaFila = cuartaFila.findElements(By.cssSelector(".rt-td"));

        System.out.println("  First Name: " + celdasCuartaFila.get(0).getText());
        System.out.println("  Last Name: " + celdasCuartaFila.get(1).getText());
        System.out.println("  Age: " + celdasCuartaFila.get(2).getText());
        System.out.println("  Email: " + celdasCuartaFila.get(3).getText());
        System.out.println("  Salary: " + celdasCuartaFila.get(4).getText());
        System.out.println("  Department: " + celdasCuartaFila.get(5).getText());
    }
    // Fin Web Tables - Caso 2

    // Inicio Web Tables - Caso 3
    @Then("doy clic en el botón Edit del segundo registro para que me muestre el modal con los datos y edito todos los datos del registro")
    public void btnEditAndShowModal(){
        WebElement btnEdit = driver.findElement(By.id("edit-record-2"));
        btnEdit.click();

        WebElement modal = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));

        WebElement nombre = driver.findElement(By.id("firstName"));
        WebElement apellido = driver.findElement(By.id("lastName"));
        WebElement correo = driver.findElement(By.id("userEmail"));
        WebElement edad = driver.findElement(By.id("age"));
        WebElement salario = driver.findElement(By.id("salary"));
        WebElement departamento = driver.findElement(By.id("department"));

        nombre.clear();
        nombre.sendKeys("Leonardo");

        apellido.clear();
        apellido.sendKeys("Bances");

        correo.clear();
        correo.sendKeys("lbances@mailinator.com");

        edad.clear();
        edad.sendKeys("21");

        salario.clear();
        salario.sendKeys("3200");

        departamento.clear();
        departamento.sendKeys("QA Automation");

        WebElement btnSutmit = driver.findElement(By.id("submit"));
        btnSutmit.click();
    }

    @And("valido que los nuevos datos sean los correctos")
    public void validateNewData(){
        WebElement cuartaFila = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='rt-tr-group'])[2]")));

        List<WebElement> celdasCuartaFila = cuartaFila.findElements(By.cssSelector(".rt-td"));

        String expectedFirstName = "Leonardo";
        String expectedLastName = "Bances";
        String expectedUserEmail = "lbances@mailinator.com";
        String expectedAge = "21";
        String expectedSalary = "3200";
        String expectedDepartment = "QA Automation";

        Assert.assertEquals(expectedFirstName, celdasCuartaFila.get(0).getText());

        Assert.assertEquals(expectedLastName, celdasCuartaFila.get(1).getText());

        Assert.assertEquals(expectedUserEmail, celdasCuartaFila.get(3).getText());

        Assert.assertEquals(expectedAge, celdasCuartaFila.get(2).getText());

        Assert.assertEquals(expectedSalary, celdasCuartaFila.get(4).getText());

        Assert.assertEquals(expectedDepartment, celdasCuartaFila.get(5).getText());
    }
    // Fin Web Tables - Caso 3

    // Inicio Links - Caso 1
    @When("selecciono la opción Links")
    public void selectLinksOption(){
        WebElement btnLinksOption = driver.findElement(By.id("item-5"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", btnLinksOption);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement clickableBtnLinks = wait.until(ExpectedConditions.elementToBeClickable(btnLinksOption));
        clickableBtnLinks.click();
    }

    @Then("debo seleccionar el textlink Home para que me redireccione al home de Demo QA")
    public void redirectHomeDemoQA(){
        WebElement textLinkHome = driver.findElement(By.linkText("Home"));
        textLinkHome.click();
    }

    @And("debe mostrarse el logo de Demo QA")
    public void validateHomeDemoQA(){
        WebElement imageDemoQA = driver.findElement(By.xpath("//img[@src='/images/Toolsqa.jpg']"));
        if(imageDemoQA.isDisplayed()){
            System.out.println("  Ingresamos a la home Demo QA");
        }
        else{
            System.out.println("  No ingresamos a la home Demo QA");
        }
    }
    // Fin Links - Caso 1

    // Inicio Links - Caso 2
    @Then("doy clic en el textlink Created")
    public void textlinkCreated(){
        WebElement textLinkCreated = driver.findElement(By.linkText("Created"));
        textLinkCreated.click();
    }

    @And("me mostrará el mensaje correspondiente a Created")
    public void showMessageCreated(){
        WebElement messageCreated = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("linkResponse")));

        String messageCreated_Obtenido = messageCreated.getText();
        String messageCreated_Esperado = "Link has responded with staus 201 and status text Created";

        Assert.assertEquals(messageCreated_Esperado, messageCreated_Obtenido);
    }
    // Fin Links - Caso 2

    // Inicio Links - Caso 3
    @Then("doy clic en el textlink No Content")
    public void textlinkNoContent(){
        WebElement textLinkNoContent = driver.findElement(By.linkText("No Content"));
        textLinkNoContent.click();
    }

    @And("me mostrará el mensaje correspondiente a No Content")
    public void showMessageNoContent(){
        WebElement messageNoContent = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("linkResponse")));

        String messageNoContent_Obtenido = messageNoContent.getText();
        String messageNoContent_Esperado = "Link has responded with staus 204 and status text No Content";

        Assert.assertEquals(messageNoContent_Esperado, messageNoContent_Obtenido);
    }
    // Fin Links - Caso 3

    // Inicio Links - Caso 4
    @Then("doy clic en el textlink Moved")
    public void textlinkMoved(){
        WebElement textLinkMoved = driver.findElement(By.linkText("Moved"));
        textLinkMoved.click();
    }

    @And("me mostrará el mensaje correspondiente a Moved")
    public void showMessageMoved(){
        WebElement messageMoved = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("linkResponse")));

        String messageMoved_Obtenido = messageMoved.getText();
        String messageMoved_Esperado = "Link has responded with staus 301 and status text Moved Permanently";

        Assert.assertEquals(messageMoved_Esperado, messageMoved_Obtenido);
    }
    // Fin Links - Caso 4

    // Inicio Links - Caso 5
    @Then("doy clic en el textlink Bad Request")
    public void textlinkBadRequest(){
        WebElement textLinkBadRequest = driver.findElement(By.linkText("Bad Request"));
        textLinkBadRequest.click();
    }

    @And("me mostrará el mensaje correspondiente a Bad Request")
    public void showMessageBadRequest(){
        WebElement messageBadRequest = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("linkResponse")));

        String messageBadRequest_Obtenido = messageBadRequest.getText();
        String messageBadRequest_Esperado = "Link has responded with staus 400 and status text Bad Request";

        Assert.assertEquals(messageBadRequest_Esperado, messageBadRequest_Obtenido);
    }
    // Fin Links - Caso 5

    // Inicio Links - Caso 6
    @Then("doy clic en el textlink Unauthorized")
    public void textlinkUnauthorized(){
        WebElement textLinkUnauthorized = driver.findElement(By.linkText("Unauthorized"));
        textLinkUnauthorized.click();
    }

    @And("me mostrará el mensaje correspondiente a Unauthorized")
    public void showMessageUnauthorized(){
        WebElement messageUnauthorized = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("linkResponse")));

        String messageUnauthorized_Obtenido = messageUnauthorized.getText();
        String messageUnauthorized_Esperado = "Link has responded with staus 401 and status text Unauthorized";

        Assert.assertEquals(messageUnauthorized_Esperado, messageUnauthorized_Obtenido);
    }
    // Fin Links - Caso 6

    // Inicio Links - Caso 7
    @Then("doy clic en el textlink Forbidden")
    public void textlinkForbidden(){
        WebElement textLinkForbidden = driver.findElement(By.linkText("Forbidden"));
        textLinkForbidden.click();
    }

    @And("me mostrará el mensaje correspondiente a Forbidden")
    public void showMessageForbidden(){
        WebElement messageForbidden = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("linkResponse")));

        String messageForbidden_Obtenido = messageForbidden.getText();
        String messageForbidden_Esperado = "Link has responded with staus 403 and status text Forbidden";

        Assert.assertEquals(messageForbidden_Esperado, messageForbidden_Obtenido);
    }
    // Fin Links - Caso 7

    // Inicio Links - Caso 8
    @Then("doy clic en el textlink Not Found")
    public void textlinkNotFound(){
        WebElement textLinkNotFound = driver.findElement(By.linkText("Not Found"));
        textLinkNotFound.click();
    }

    @And("me mostrará el mensaje correspondiente a Not Found")
    public void showMessageNotFound(){
        WebElement messageNotFound = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("linkResponse")));

        String messageNotFound_Obtenido = messageNotFound.getText();
        String messageNotFound_Esperado = "Link has responded with staus 404 and status text Not Found";

        Assert.assertEquals(messageNotFound_Esperado, messageNotFound_Obtenido);
    }
    // Fin Links - Caso 8
}