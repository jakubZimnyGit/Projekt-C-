using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KalkulatorWinforms.UnitTest
{
    internal class KalkulatorTests
    {
        [Test]
        public void Add_WhenCalled_ShouldReturnTrue()
        {
            
            //arrange
            var calculations = new Calculations();

            //act
            var result = calculations.Addition(1, 2);

            //assert
            Assert.AreEqual(3,result);
        }
        [Test]
        public void Substract_WhenCalled_ShouldReturnTrue()
        {
            var calculations = new Calculations();
            var result = calculations.Substraction(2, 1);
            Assert.AreEqual(1, result);
        }
        [Test]
        public void Multiply_WhenCalled_ShouldReturnTrue()
        {
            var calculations = new Calculations();
            var result = calculations.Multiplication(1, 2);
            Assert.AreEqual(2, result);
        }
        [Test]
        public void Divide_WhenCalled_ShouldReturnTrue()
        {
            var calculations = new Calculations();
            var result = calculations.Division(2, 1);
            Assert.AreEqual(2, result);
        }
    }
}
