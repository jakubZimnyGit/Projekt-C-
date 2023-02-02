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
            bool gameOn = true;
            while (gameOn)
            {

                ZasadyGry zasady = new ZasadyGry();
                string[,] plansza = new string[zasady.wiersze, zasady.kolumny];
                UzupelnijPlansze(plansza);
                Player gracz1 = new Player(1);
                Player gracz2 = new Player(2);
                int iloscTur = zasady.wiersze * zasady.kolumny;
                Player aktualnyGracz;
                int tura = 1;
                WyswietlPlansze(plansza);
                for (int i = 0; i < iloscTur; i++)
                {
                    if (tura % 2 == 0)
                    {
                        aktualnyGracz = gracz2;
                    }
                    else
                    {
                        aktualnyGracz = gracz1;
                    }
                    Console.WriteLine($"Tura gracza {aktualnyGracz.imie}\n");
                    int wiersz = Silnik.PobierzInt("Podaj wiersz ") - 1;
                    int kolumna = Silnik.PobierzInt("Podaj kolumnę ") - 1;
                    while (!Silnik.CzyMoznaWykonacRuch(wiersz, kolumna, plansza))
                    {
                        Console.WriteLine("Proszę podać inne koordynaty");
                        wiersz = Silnik.PobierzInt("Podaj wiersz ") - 1;
                        kolumna = Silnik.PobierzInt("Podaj kolumnę ") - 1;
                    }
                    plansza[wiersz, kolumna] = aktualnyGracz.symbol;
                    if (Silnik.SprawdzWygrana(wiersz, kolumna, plansza, aktualnyGracz, zasady.seriaDoWygranej))
                    {
                        break;
                    }
                    else
                    {
                        tura++;
                    }
                    Console.Clear();
                    WyswietlPlansze(plansza);
                }
                Console.WriteLine("KONIEC GRY!!! ");
                gameOn = CzyChceszZagracPonownie();
                Console.Clear();
            }

        }
        public static void UzupelnijPlansze(string[,] plansza)      
        {
            for (int i = 0; i < plansza.GetLength(0); i++)
            {
                for (int j = 0; j < plansza.GetLength(1); j++)
                {
                    plansza[i, j] = " ";
                }
            }
        }
        public static void WyswietlPlansze(string[,] plansza)
        {
            for (int i = 0; i < plansza.GetLength(0); i++)
            {
                for (int j = 0; j < plansza.GetLength(1); j++)
                {
                    Console.Write($"[{plansza[i, j]}]");
                }
                Console.WriteLine();
            }
        }
        static bool CzyChceszZagracPonownie()
        {
            Console.WriteLine("Czy chcesz zagrać ponownie? (t/n): ");
            string odp = Console.ReadLine();
            while (true)
            {
                if (odp == "t")
                {
                    return true;
                }
                else if (odp == "n")
                {
                    return false;
                }
                else
                {
                    Console.WriteLine("Nieodpowienie wyrażenie, proszę spróbować ponownie (t/n): ");
                    odp = Console.ReadLine();
                }
            }
        }


    }
}
