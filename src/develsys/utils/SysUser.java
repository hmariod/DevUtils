/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package develsys.utils;

/**
 *
 * @author hmariod
 */
public class SysUser {
    public static enum statusEnum{
        LOGGED,
        AWAY
    };
    public statusEnum status;
    public String userName;
    
    public SysUser(){
        userName = "";
        status = statusEnum.AWAY;
    }
    
}
