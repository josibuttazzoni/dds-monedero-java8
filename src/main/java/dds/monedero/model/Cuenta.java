package dds.monedero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;

public class Cuenta {

  private double saldo = 0;
  private List<Movimiento> movimientos = new ArrayList<>();

  public Cuenta(double montoInicial) {
    saldo = montoInicial;
  }

  public void setMovimientos(List<Movimiento> movimientos) {
    this.movimientos = movimientos;
  }

  public void depositar(double cuanto) {
	  
    this.validarMontoPositivo(cuanto);
    this.validarMaximosDepositos();
    new Movimiento(LocalDate.now(), cuanto, true).agregateA(this);
  }

  public void sacar(double cuanto) {
    validarMontoPositivo(cuanto);
    validarFondosSuficientes(cuanto);
    validarMaximoExtraccionDiaria(cuanto);
    new Movimiento(LocalDate.now(), cuanto, false).agregateA(this);
  }

  public void agregarMovimiento(Movimiento movimiento) {
    movimientos.add(movimiento);
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return getMovimientos().stream()
        .filter(movimiento -> !movimiento.isDeposito() && movimiento.getFecha().equals(fecha))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }

  public List<Movimiento> getMovimientos() {
    return movimientos;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }
  
  public void validarMontoPositivo (double unMonto) {
	  if (unMonto <= 0) {
	      throw new MontoNegativoException(unMonto + ": el monto a ingresar debe ser un valor positivo");
	    }
  }
  
  public void validarMaximosDepositos () {
	  if (getMovimientos().stream().filter(movimiento -> movimiento.isDeposito()).count() >= 3) {
	      throw new MaximaCantidadDepositosException("Ya excedio los " + 3 + " depositos diarios");
	    }
  }
  
  public void validarFondosSuficientes (double unMonto) {
	  if (getSaldo() - unMonto < 0) {
	      throw new SaldoMenorException("No puede sacar mas de " + getSaldo() + " $");
	    }
  }
  
  public void validarMaximoExtraccionDiaria (double unMonto) {
	  double montoExtraidoHoy = getMontoExtraidoA(LocalDate.now());
	    double limite = 1000 - montoExtraidoHoy;
	    if (unMonto > limite) {
	      throw new MaximoExtraccionDiarioException("No puede extraer mas de $ " + 1000
	          + " diarios, limite: " + limite);
	    }
  }
}