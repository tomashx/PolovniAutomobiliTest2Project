import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import pages.*;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testingData.StandardData;

public class TestCase02HardPolAuto extends BaseTestClass {

        private WebDriver driver;
        private String password;

        private String registrationMessage;
        private String emailProton;

        private String name;
        private String lastName;
        private String city;
        private String zip;
        private String address;
        private String cellphone;
        private String userDataSaveMessage;



    @BeforeMethod
        public void setUpTest(){
            driver = setUpDriver();
            emailProton = StandardData.EMAIL;
            password = StandardData.EMAIL_PASSWORD;
            registrationMessage = StandardData.SUCCESSFULL_REGISTRATION_MESSAGE;
            userDataSaveMessage = StandardData.SUCCESSFULL_SAVE_USER_DATA_MESSAGE;
            name = StandardData.NAME;
            lastName = StandardData.LAST_NAME;
            address = StandardData.ADDRESS;
            city = StandardData.CITY;
            zip = StandardData.ZIP;
            cellphone = StandardData.CELLPHONE;

        }

        @Test
        public void TC_02()  {
            Index glavniTab = new Index(driver);
            Index zatvoriAdBlockTab = glavniTab.zatvoriTabove();

            //Otvori web stranu
            Index index = new Index(driver).open();
            Login login = new Login(driver);


            Registracija registracija = new Registracija(driver);

            //Klikni na postavi oglas
            Index buttonPostaviOglas = index.PostaviOglasClick();

            //Klikni na registruj se
            Login buttonRegistrujSe = login.RegistrujSeClick();

            //Unesi random email
            registracija.setRandomEmail();
            System.out.println("Ovo je generisani mail: " + registracija.privremeniMail);

            //Unesi password
            Registracija typePasword = registracija.typePassword(password);

            //Ponovi password
            Registracija retypePassword = registracija.retypePassword(password);

            //Potvrda saglasnosti tri checkbox-a
            Registracija tickAllCheckboxes = registracija.tosFirstCheckboxClick().easySaleConsentSecondCheckboxClick().easyBuyConsentThirdCheckboxClick().registrujSeClick();

            //Verifikacija poruke nakon registracije
            String registrationMessageText = Registracija.getSuccessfullRegistratonMessageText();
            System.out.println("Successfull registration message: " + registrationMessageText);
            Assert.assertEquals(registrationMessageText, registrationMessage, "ERROR - MESSAGE NOT SHOWN");

            //Otvori protonmail u novom tabu
            ProtonMail protonmail = new ProtonMail(driver);
            ProtonMail openNewTab = protonmail.OpenProtonMailInNewTab();
            ProtonMail signInButtonClick = protonmail.SignInProtonMailClick();
            ProtonMail fillEmailAndPassword = protonmail.typeEmailProtonMail(emailProton).typePasswordProtonMail(password).signInButtonClick();
            ProtonMail waitForInboxUrl = protonmail.emailInboxWait();
            ProtonMail activationMailClick = protonmail.unreadMailClick().activationMailClick();
            String veryLink = protonmail.activationLinkClick();
            System.out.println(veryLink);
            ProtonMail verificationLinkOpen = protonmail.OpenVerificationLinkNewTab(veryLink);
            ProtonMail zatvoriTaboveOstale = protonmail.zatvoriTabove();
            Registracija anketaUradi = registracija.chkBoxesClick();
            Registracija popuniPodatkeOKorisniku = registracija.fillUserRequiredFields(name, lastName, address, city, zip, cellphone);
            Registracija odaberiOkrug = registracija.clickRegion().selectSumadijskiOkrug();
            Registracija sacuvajPodatke = registracija.submit();


            //Verifikacija poruke nakon cuvanja podataka
            String saveUserDataSuccessfullMessageText = Registracija.getSuccessfullUserDataSaveMessageText();
            System.out.println("Successfull registration message: " + saveUserDataSuccessfullMessageText);
            Assert.assertEquals(saveUserDataSuccessfullMessageText, userDataSaveMessage, "ERROR - MESSAGE NOT SHOWN");


            Registracija userMenuDropDownClick = registracija.userMenuDropDownClick();
            Registracija hoverOverUserMenuAndLogout = registracija.hoverUserToShowLogout();
            String waitForLogout = registracija.waitForOdjaviSe();
            Registracija logoutClick = registracija.LogoutClick();


            String openIndexPage = index.open().waitForPrijaviSe();
            Index userMenuLogin = index.userMenuDropDownClick();


            //Unesi random email
            Login typeEmailLogin = login.typeEmail(registracija.privremeniMail);
            //Klikni dalje
            Login daljeClick = login.DaljeClick();
            //Unesi password
            Login typePaswordLogin = login.typePassword(password);
            //Klikni prijavi se
            Login prijaviSeLoginClick = login.PrijaviSeClick();

            Index pokupiMail = index.hoverUserToShowLogout();

            String userEmail = index.getUserEmailAfterLogin();
            System.out.println("Find element user Email. Element found: " + userEmail);
            Assert.assertEquals(userEmail, registracija.privremeniMail, "ERROR - EMAIL ADRESSES ARE NOT SAME");

            ProtonMail openNewTabAgain = protonmail.OpenProtonMailInNewTab();
            ProtonMail signInButtonClickAgain = protonmail.SignInProtonMailClick();
            ProtonMail waitForInboxUrlAgain = protonmail.emailInboxWait();
            ProtonMail selectAndDelete = protonmail.selectAllMessages().deleteMailButtonClick();

        }
        @AfterMethod(alwaysRun = true)
        public void tearDownTest(){
            quitDriver(driver);
        }
}
