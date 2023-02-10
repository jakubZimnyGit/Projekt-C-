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
        public Form1()
        {
            InitializeComponent();
            tbResult.Text = "0";
        }

        private void OnNumberBtnClick(object sender, EventArgs e)
        {
            string number = (sender as Button).Text;
            if (tbResult.Text == "0" && number != ",")
            {
                tbResult.Text = null;
            }
            tbResult.Text += number;
        }

        private void OnCleanBtnClick(object sender, EventArgs e)
        {
            tbResult.Text = "0";
        }
    }
}
