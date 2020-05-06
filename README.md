## Monedero

### Contexto

Este repositorio contiene el código de un _monedero virtual_ en Java 8, al que podemos agregarle y quitarle dinero, a través 
de los métodos `Monedero.sacar` y `Monedero.poner`, respectivamente. 
Pero hay algunos problemas: por un lado el código no está muy bien testeado, y por el otro, hay numeros _code smells_. 

### Consigna

Tenés seis tareas: 

 1. :fork_and_knife: Hacé un _fork_ de este repositorio (presionando desde Github el botón Fork)
 2. :arrow_down: Descargalo y construí el proyecto, utilizando `maven`
 2. :nose: Identificá y anotá todos los _code smells_ que encuentres 
 3. :test_tube: Agregá los tests faltantes y mejorá los existentes. 
     * :eyes: Ojo: ¡un test sin ningún tipo de aserción está incompleto!
 4. :rescue_worker_helmet: Corregí smells, de a un commit por vez. 
 5. :arrow_up: Subí todos los cambios a tu _fork_
 
 

 ## Code Smells Identificados:

1. Misplaced method (En este caso sirve para no romper el encapsulamiento)
```java
 public void agregarMovimiento(LocalDate fecha, double cuanto, boolean esDeposito) {
    Movimiento movimiento = new Movimiento(fecha, cuanto, esDeposito);
    movimientos.add(movimiento);
  }
```
2. Duplicated code 
```java
 public Cuenta() {}
 public Cuenta(double montoInicial) {}
```
3. Long method
```java
public void poner(double cuanto) {}
public void sacar(double cuanto) {}
```
4. Long parameter list
```java
public void agregarMovimiento(LocalDate fecha, double cuanto, boolean esDeposito) {}
```
5. Type test (Podrian existir las clases Deposito y Extraccion que hereden de una abstracta Movimiento)
6. Message Chains (Se rompe el encapsulamiento)
```java
 public void agregateA(Cuenta cuenta) {
    cuenta.setSaldo(calcularValor(cuenta));
    cuenta.agregarMovimiento(fecha, monto, esDeposito);
  }
```
7. Primitive obsession (variable boolean esDeposito en la clase Movimiento)




  


