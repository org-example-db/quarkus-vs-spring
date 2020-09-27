package eu.davidemartorana.performance.gatling

import java.time.LocalDate

object FunctionsHelper {

  def getMinimum(first: LocalDate, second: LocalDate) : LocalDate = {
    if (first.isBefore(second) ) {
      first;
    } else {
      second
    };
  }

  def getMaximum(first: LocalDate, second: LocalDate) : LocalDate = {
    if (first.isAfter(second) ) {
      first;
    } else {
      second
    };
  }

}
