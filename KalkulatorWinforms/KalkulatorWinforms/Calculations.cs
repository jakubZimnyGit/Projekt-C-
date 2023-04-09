using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static KalkulatorWinforms.Kalkulator;

namespace KalkulatorWinforms
{
    public class Calculations
    {
        public double Addition(double firstNumber, double secondNumber)
        {
            return firstNumber + secondNumber;             
        }
        public double Substraction(double firstNumber, double secondNumber)
        {
            return firstNumber - secondNumber;
        }
        public double Multiplication(double firstNumber, double secondNumber)
        {
            return firstNumber * secondNumber;
        }
        public double Division(double firstNumber, double secondNumber)
        {
            if (secondNumber == 0)
            {
                return 0;
            }
            return firstNumber / secondNumber;
        }
    }
}
