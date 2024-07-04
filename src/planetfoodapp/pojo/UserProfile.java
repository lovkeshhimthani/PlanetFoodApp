/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfoodapp.pojo;

/**
 *
 * @author HP
 */
public class UserProfile {
    private static String UserId;
    private static String usertype;

    public static String getUserId() {
        return UserId;
    }

    public static void setUserId(String UserId) {
        UserProfile.UserId = UserId;
    }
    

    public static String getUsertype() {
        return usertype;
    }

    public static void setUsertype(String usertype) {
        UserProfile.usertype = usertype;
    }
}
