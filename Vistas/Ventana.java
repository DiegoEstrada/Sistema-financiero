
 /*
 + * To change this license header, choose License Headers in Project Properties.
 + * To change this template file, choose Tools | Templates
 + * and open the template in the editor.
 + */
 package Vistas;
 
 import javax.swing.JOptionPane;
 
 public class Ventana {
     
     public static void ShowInformationMessage(String msg)
     {
         JOptionPane.showMessageDialog(null, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
     }
     
     public static void ShowErrorMessage(String msg)
     {
         JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
     }
     
     public static void ShowWarningMessage(String msg)
     {
         JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.WARNING_MESSAGE);
     }
     
     public static String setValue(String msg)
     {
         return JOptionPane.showInputDialog(null,msg);
     }
 }