using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicTacToe
{
    internal class ZasadyGry
    {
        public int wiersze { get; set; }
        public int kolumny { get; set; }
        public int seriaDoWygranej { get; set; }

        public ZasadyGry()       
        {
            Console.WriteLine("*Ustawienia planszy*\n");
            wiersze = Silnik.PobierzInt("Podaj liczbę wierszy: ");
            kolumny = Silnik.PobierzInt("Podaj liczbę kolumn: ");
            seriaDoWygranej = Silnik.PobierzInt("Podaj warunek zwycięstwa: ");
               
        }
    }
}
