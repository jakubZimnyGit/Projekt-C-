using System;
using System.Collections.Generic;
using System.Data.Odbc;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicTacToe
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int x = PobierzInt("Podaj dowolny nr: ");
            Console.WriteLine(x);
        }
        static string PobierzString(string komunikat)
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
        static int PobierzInt(string komunikat)
        {
            Console.WriteLine(komunikat);
            int odp;
            while (!int.TryParse(Console.ReadLine(), out odp))
            {
                Console.WriteLine("Wystąpił błąd. Proszę spróbować ponownie: ");
            }
            return odp;
        }
    }
}
