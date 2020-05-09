package dds.monedero.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;

public class MonederoTest {
  private Cuenta cuenta;

  @Before
  public void init() {
    cuenta = new Cuenta(0);
  }

  @Test
  public void ElSaldoEs1500LuegoDePonerDichoMonto() {
    cuenta.depositar(1500);
    Assert.assertEquals(1500,cuenta.getSaldo(),0);
  }

  @Test(expected = MontoNegativoException.class)
  public void AlIntentarPonerUnMontoNegativoSeLanzaExcepcionMontoNegativo() {
    cuenta.depositar(-1500);
  }

  @Test(expected = MaximaCantidadDepositosException.class)
  public void AlHacer3DepositosSeLanzaExcepcionMaximaCantidadDepositos() {
    cuenta.depositar(1500);
    cuenta.depositar(456);
    cuenta.depositar(1900);
    cuenta.depositar(245);
  }

  @Test(expected = SaldoMenorException.class)
  public void AlIntentarSacarMasDineroDelSaldoSeLanzaExcepcionSaldoMenor() {
    cuenta.setSaldo(90);
    cuenta.sacar(1001);
  }

  @Test(expected = MaximoExtraccionDiarioException.class)
  public void AlIntentarSacarMasDelMaximoSeLanzaExcepcionMaximoExtraccionDiaria() {
    cuenta.setSaldo(5000);
    cuenta.sacar(1001);
  }

  @Test(expected = MontoNegativoException.class)
  public void AlIntentarSacarUnMontoNegativoSeLanzaExcepcionMontoNegativo() {
    cuenta.sacar(-500);
  }

}