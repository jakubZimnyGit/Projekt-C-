using System;
using System.Collections.Generic;
using System.Data.Odbc;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicTacToe
{
    internal class Program
    {
        static void Main(string[] args)
        {
            ZasadyGry zasady = ZasadyGry.UstawZasady();
            string[,] plansza = new string[zasady.wiersze, zasady.kolumny];
            WyswietlPlansze(plansza);
        }
        public static string PobierzString(string komunikat)
        {
            Console.WriteLine(komunikat);
            string odp = Console.ReadLine();
            while (odp == null)
            {
                Console.Write("Wystąpił błąd, proszę spróbować ponownie: ");
                odp = Console.ReadLine();
            }
            return odp;
        }
        public static int PobierzInt(string komunikat)
        {
            Console.WriteLine(komunikat);
            int odp;
            while (!int.TryParse(Console.ReadLine(), out odp))
            {
                Console.WriteLine("Wystąpił błąd. Proszę spróbować ponownie: ");
            }
            return odp;
        }
        static void WyswietlPlansze(string[,] plansza)
        {
            for (int i = 0; i < plansza.GetLength(0); i++)
            {
                for (int j = 0; j < plansza.GetLength(1); j++)
                {
                    Console.Write($"[{plansza[i,j]}]");
                }
                Console.WriteLine();
            }
        }
    }
}
