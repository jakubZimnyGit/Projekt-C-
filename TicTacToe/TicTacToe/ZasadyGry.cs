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

        public static ZasadyGry UstawZasady()       //Funckcja ma za zadanie ułatwić inicjalizowanie obiektu typu ZasadyGry.
        {
            ZasadyGry zasady = new ZasadyGry();
            Console.WriteLine("*Ustawienia planszy*\n");
            zasady.wiersze = Silnik.PobierzInt("Podaj liczbę wierszy: ");
            zasady.kolumny = Silnik.PobierzInt("Podaj liczbę kolumn: ");
            zasady.seriaDoWygranej = Silnik.PobierzInt("Podaj warunek zwycięstwa: ");
            return zasady;   
        }
    }
}
