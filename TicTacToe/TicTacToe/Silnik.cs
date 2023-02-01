using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicTacToe
{
    internal class Silnik
    {
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
        public static bool CzyMoznaWykonacRuch(int wiersz, int kolumna, string[,] plansza)
        {
            try
            {
                if (plansza[wiersz,kolumna] == " ")
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch { return false; }
        }
        static string[] PobierzZlewejDoPrawej(int wiersz, int kolumna, string[,] plansza, int SeriaDoWygranej)
        {

            int wierszStart = wiersz - (SeriaDoWygranej - 1);
            int wierszKoniec = wiersz + SeriaDoWygranej; 

            List<string> symbole = new List<string>();

            for (int i = wierszStart; i < wierszKoniec; i++)
            {
                int kolumnaStart = kolumna - (SeriaDoWygranej - 1);
                int kolumnaKoniec = kolumna - (SeriaDoWygranej - 2);

                for (int j = kolumnaStart; j < kolumnaKoniec; j++)
                {
                    try
                    {
                        symbole.Add(plansza[i, j]);
                    }
                    catch (IndexOutOfRangeException)
                    {
                        symbole.Add("NIC");
                    }
                }
                kolumna++;
            }
            string[] symbolkiTest = symbole.ToArray();

            /* foreach (string symbolek in symbolkiTest)   Użyte do sprawdzenia działania funkcji. Pobierane symbole zostają wyświetlane na ekranie konsoli
             {                                             dzięki czemu można łatwiej wychwycić błędy i skorygować działanie algorytmu.   
                 Console.Write($"{symbolek}, ");
             }
             Console.WriteLine();
             */
            return symbole.ToArray();

        }
        static string[] PobierzZprawejDoLewej(int wiersz, int kolumna, string[,] plansza, int SeriaDoWygranej)
        {
            int wierszStart = wiersz - (SeriaDoWygranej - 1); 
            int wierszKoniec = wiersz + SeriaDoWygranej; 

            List<string> symbole = new List<string>();

            for (int i = wierszStart; i < wierszKoniec; i++)
            {
                int kolumnaStart = kolumna + (SeriaDoWygranej - 1);
                int kolumnaKoniec = kolumna + (SeriaDoWygranej - 2);

                for (int j = kolumnaStart; j > kolumnaKoniec; j--)
                {
                    try
                    {
                        symbole.Add(plansza[i, j]);
                    }
                    catch (IndexOutOfRangeException)
                    {
                        symbole.Add("NIC");
                    }
                }
                kolumna--;
            }
            string[] symbolkiTest = symbole.ToArray();
            /*
            foreach (string symbolek in symbolkiTest)
            {
                Console.Write($"{symbolek}, ");
            }
            */

            return symbole.ToArray();
        }
        static string[] PobierzPionowo(int wiersz, int kolumna, string[,] plansza, int SeriaDoWygranej)
        
        {
            int wierszStart = wiersz - (SeriaDoWygranej - 1);
            int wierszKoniec = wiersz + SeriaDoWygranej;

            List<string> symbole = new List<string>();

            for (int i = wierszStart; i < wierszKoniec; i++)
            {
                int j = kolumna;
                try
                {
                    symbole.Add(plansza[i, j]);
                }
                catch (IndexOutOfRangeException)
                {
                    symbole.Add("NIC");
                }
            }
            string[] symbolkiTest = symbole.ToArray();



            return symbolkiTest;

        }
        static string[] PobierzPoziomo(int wiersz, int kolumna, string[,] plansza, int SeriaDoWygranej)
        {
            int kolumnaStart = kolumna - (SeriaDoWygranej - 1);
            int kolumnaKoniec = kolumna + SeriaDoWygranej;

            List<string> symbole = new List<string>();
            int i = wiersz;

            for (int j = kolumnaStart; j < kolumnaKoniec; j++)
            {
                try
                {
                    symbole.Add(plansza[i, j]);
                }
                catch (IndexOutOfRangeException)
                {
                    symbole.Add("NIC");
                }
            }
            return symbole.ToArray();


        }
        static bool SprawdzWarunkiWygranej(string[] symbolePobraneZPlanszy, Player gracz, int SeriaPunktowDoWygranej)
        {
            int warunek = SeriaPunktowDoWygranej;
            int wynik = 0;
            for (int i = 0; i < symbolePobraneZPlanszy.GetLength(0); i++)
            {
                if (symbolePobraneZPlanszy[i] == gracz.symbol)
                {
                    wynik++;
                    if (wynik == warunek)
                    {
                        return true;
                    }
                }
                else
                {
                    wynik = 0;
                }

            }
            return false;


        }
        public static bool SprawdzWygrana(int wiersz, int kolumna, string[,] plansza, Player gracz, int SeriaDoWygranej)
        {
            if (SprawdzWarunkiWygranej(PobierzZlewejDoPrawej(wiersz, kolumna, plansza, SeriaDoWygranej), gracz, SeriaDoWygranej) ||
                SprawdzWarunkiWygranej(PobierzZprawejDoLewej(wiersz, kolumna, plansza, SeriaDoWygranej), gracz, SeriaDoWygranej) ||
                SprawdzWarunkiWygranej(PobierzPionowo(wiersz, kolumna, plansza, SeriaDoWygranej), gracz, SeriaDoWygranej) ||
                SprawdzWarunkiWygranej(PobierzPoziomo(wiersz, kolumna, plansza, SeriaDoWygranej), gracz, SeriaDoWygranej))
            {
                Console.Clear();
                Program.WyswietlPlansze(plansza);
                Console.WriteLine($"Zwyciężył gracz {gracz.imie}!");
                return true;
            }
            else
            {
                return false;
            }
        }

    }
}
