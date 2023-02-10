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
    public partial class Form1 : Form
    {
        public enum Operation
        {
            None,
            Addition,
            Substraction,
            Multiplication,
            Dividion
        }

        private bool commaPlaced = false;
        private string firstNumber;
        private string secondNumber;
        private Operation currentOperation = Operation.None;
        private bool isResultOnTheScreen = false;
        public Form1()
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
                    currentOperation = Operation.Dividion;
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
            switch (currentOperation)
            {
                case Operation.Addition:
                    return firstNumber + secondNumber;
                case Operation.Substraction:
                    return firstNumber - secondNumber;
                case Operation.Multiplication:
                    return firstNumber * secondNumber;
                case Operation.Dividion:
                    if (secondNumber == 0)
                    {
                        return 0;
                    }
                    return firstNumber / secondNumber;
            }
            return 0;
        }
    }
}
