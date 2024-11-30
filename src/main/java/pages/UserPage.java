package pages;

import org.openqa.selenium.By;

public class UserPage {
    public static By obj_AddUsers_Btn = By.xpath("//div[text()='Add User']");
    public static By obj_User_FirstName_Edit = By.xpath("//input[@name='firstName']");
    public static By obj_User_LastName_Edit = By.xpath("//input[@name='lastName']");
    public static By obj_User_Email_Edit = By.xpath("//input[@name='email']");
    public static By obj_User_UserName_Edit = By.xpath("//input[@name='username']");
    public static By obj_User_Password_Edit = By.xpath("//input[@name='password']");
    public static By obj_User_RetypePassword_Edit = By.xpath("//input[@name='passwordCopy']");
    public static By obj_CreateUser_Btn = By.xpath("//span[text()='Create User']");
    public static By obj_DeleteUser_Btn = By.xpath("//button[contains(text(), 'Delete User')]");
    public static String obj_userName_Link = "//div[@class='name']/span[text()='%s']";
    public static By obj_SaveChanges_Btn = By.xpath("//span[text()='Save Changes']");
}
