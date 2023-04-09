using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;



namespace KalkulatorWinforms
{
    public partial class Kalkulator : Form
    {
        public enum Operation
        {
            None,
            Addition,
            Substraction,
            Multiplication,
            Division
        }

        private bool commaPlaced = false;
        private string firstNumber;
        private string secondNumber;
        private Operation currentOperation = Operation.None;
        private bool isResultOnTheScreen = false;
        public Kalkulator()
        {
            InitializeComponent();
            tbResult.Text = "0";
        }

        private void OnNumberBtnClick(object sender, EventArgs e)
        {
            string number = (sender as Button).Text;
            if (isResultOnTheScreen)
            {
                tbResult.Text = "0";
                isResultOnTheScreen=false;
            }
            if (tbResult.Text == "0")
            {
                tbResult.Text = null;
            }
            if (currentOperation != Operation.None)
            {
                secondNumber += number;
            }
            
            tbResult.Text += number;
        }

        private void OnCleanBtnClick(object sender, EventArgs e)
        {
            tbResult.Text = "0";
            commaPlaced = false;
            SetOperationBtnState(true);
        }

        private void OnCommaBtnClick(object sender, EventArgs e)
        {
            if (isResultOnTheScreen)
            {
                tbResult.Text = "0";
                isResultOnTheScreen = false;
            }
            if (!commaPlaced)
            {
                tbResult.Text += ".";
                commaPlaced = true;
            }
            if (currentOperation != Operation.None)
            {
                secondNumber += ".";
            }
        }

        private void OnOperationBtnClick(object sender, EventArgs e)
        {
            firstNumber = tbResult.Text;
            string operation = (sender as Button).Text;
            switch (operation)
            {
                case "+":
                    currentOperation = Operation.Addition;
                    break;
                case "-":
                    currentOperation = Operation.Substraction;
                    break;
                case "x":
                    currentOperation = Operation.Multiplication;
                    break;
                case "÷":
                    currentOperation = Operation.Division;
                    break;
            }

            tbResult.Text += operation;
            SetOperationBtnState(false);
            commaPlaced = false;
            
        }
        void SetOperationBtnState(bool Value)
        {
            btnAdd.Enabled = Value;
            btnSubstract.Enabled = Value;
            btnMultiply.Enabled = Value;
            btnDivide.Enabled = Value;
        }

        private void btnResult_Click(object sender, EventArgs e)
        {
            double first = double.Parse(firstNumber);
            double second = double.Parse(secondNumber);
            string wynik = Calculate(first, second).ToString();
            tbResult.Text = wynik;
            isResultOnTheScreen = true;
            SetOperationBtnState(true);
            currentOperation = Operation.None;
            commaPlaced = false;
            firstNumber = "0";
            secondNumber = "0";
        }
        private double Calculate(double firstNumber, double secondNumber)
        {
            Calculations calculations = new Calculations();
                switch (currentOperation)
                {
                    case Operation.Addition:
                        return calculations.Addition(firstNumber, secondNumber);
                    case Operation.Substraction:
                        return calculations.Substraction(firstNumber, secondNumber);
                    case Operation.Multiplication:
                        return calculations.Multiplication(firstNumber, secondNumber);
                    case Operation.Division:
                        return calculations.Division(firstNumber, secondNumber);
                }
            return 0;
        }
    }
}
