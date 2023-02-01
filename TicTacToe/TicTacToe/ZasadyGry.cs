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

        public static ZasadyGry UstawZasady()
        {
            ZasadyGry zasady = new ZasadyGry();
            Console.WriteLine("Ustawienia planszy: ");
            zasady.wiersze = Program.PobierzInt("Podaj liczbę wierszy (min. 2): ");
            zasady.kolumny = Program.PobierzInt("Podaj liczbę kolumn (min. 2): ");
            zasady.seriaDoWygranej = Program.PobierzInt("Podaj warunek zwycięstwa (min. 2): ");
            return zasady;   
        }
    }
}
