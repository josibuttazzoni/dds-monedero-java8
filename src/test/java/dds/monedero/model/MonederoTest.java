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
    cuenta = new Cuenta();
  }

  @Test
  public void ElSaldoEs1500LuegoDePonerDichoMonto() {
    cuenta.poner(1500);
    Assert.assertEquals(1500.0,cuenta.getSaldo());
  }

  @Test(expected = MontoNegativoException.class)
  public void AlIntentarPonerUnMontoNegativoSeLanzaExcepcionMontoNegativo() {
    cuenta.poner(-1500);
  }

  @Test(expected = MaximaCantidadDepositosException.class)
  public void AlHacer3DepositosSeLanzaExcepcionMaximaCantidadDepositos() {
    cuenta.poner(1500);
    cuenta.poner(456);
    cuenta.poner(1900);
    cuenta.poner(245);
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