package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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
}