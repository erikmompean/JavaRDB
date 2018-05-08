/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Erik
 */
public class Outputs {
    static public void welcome(){
        System.out.println("Bienvenido a FlowerErik");
    }
    
    static public void firstMenu(){
        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Registrarse");
        System.out.print("Que desea hacer?");
    }
    
    public static void loginEmail(){
        System.out.println("Introduzca su email");
    }
    
    public static void loginPassword(){
        System.out.println("Introduzca su contraseña");
    }
    
    public static void registerEmail(){
        System.out.println("Introduzca su email");
    }
    
    public static void registerPassword(){
        System.out.println("Introduzca una contraseña");
    }
    
    public static void registerRePassword(){
        System.out.println("Repita la contraseña");
    }
    
    public static void sqlExceptionMessage(){
        System.out.println("Ha habido un error al connectar con la base de datos, intentelo de nuevo o mas tarde");
    }
    
    public static void registerSuccessful(){
        System.out.println("Se ha creado la cuenta correctamente");
    }
    
    public static void registerFail(){
        System.out.println("Ha habido un problema creando la el registro, intentelo de nuevo o mas tarde");
    }
    
    public static void appMenu(){
        System.out.println("--Panel de control--");
        System.out.println("1. Solicitar un pedido");
        System.out.println("2. Ver mis pedidos");
        System.out.println("3. Editar un pedido");
        System.out.println("4. Cancelar un pedido");
        System.out.println("5. Salir");
        System.out.println("Que desea hacer?");
    }

    public static void loginSucces() {
        System.out.println("Sesion iniciada correctamente");
    }

    public static void loginFail() {
        System.out.println("Email o contraseña incorrectos");
    }

    public static void selectFlower() {
        System.out.println("Que flor desea comprar?(-1 para Salir)");
    }

    public static void selectValidOption() {
        System.out.println("Porfavor seleccione una opcion valida");
    }

    public static void selectedFlower(String name) {
        System.out.println("Has seleccionado: " + name);
        System.out.println("Que cantidad quiere?(-1 para Salir) ");
    }

    public static void zeroSelected() {
        System.out.println("Usted ha seleccionado 0. Saliendo al menu...");
    }

    public static void getAddress() {
        System.out.println("Introduzca la direccion de entrega:(vacia para Salir) ");
    }

    public static void orderCreated() {
        System.out.println("Se ha creado el pedido correctamente");
    }

    public static void orderNotCreated() {
        System.out.println("Ha habido un problema con la creacion de su pedido. Intentelo de nuevo o mas tarde");
    }

    public static void notEnoughStock() {
        System.out.println("No hay suficientes flores. Porfavor revise el stock en otro momento");
    }

    public static void passwordsNotEqual() {
        System.out.println("Las contraseñas no coinciden");
    }

    public static void emailExist() {
        System.out.println("El email ya esta registrado en nuestra base de datos");
    }
    
    
}
