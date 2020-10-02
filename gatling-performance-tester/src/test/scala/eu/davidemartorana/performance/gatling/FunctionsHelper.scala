package eu.davidemartorana.performance.gatling

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object FunctionsHelper {

  def getMinimum(first: String, second: String) : String = {

    val firstDate: LocalDate = LocalDate.parse(first, DateTimeFormatter.ISO_DATE);
    val secondDate: LocalDate = LocalDate.parse(second, DateTimeFormatter.ISO_DATE);

    if (firstDate.isBefore(secondDate) ) {
      firstDate.format(DateTimeFormatter.ISO_DATE);
    } else {
      secondDate.format(DateTimeFormatter.ISO_DATE)
    };
  }

  def getMaximum(first: String, second: String) : String = {

    val firstDate: LocalDate = LocalDate.parse(first, DateTimeFormatter.ISO_DATE);
    val secondDate: LocalDate = LocalDate.parse(second, DateTimeFormatter.ISO_DATE);

    if (firstDate.isAfter(secondDate) ) {
      firstDate.format(DateTimeFormatter.ISO_DATE);
    } else {
      secondDate.format(DateTimeFormatter.ISO_DATE)
    };
  }

}
