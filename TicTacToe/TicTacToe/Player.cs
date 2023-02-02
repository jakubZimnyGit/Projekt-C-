using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicTacToe
{
    internal class Player
    {
        public string imie { get; set; }
        public string symbol { get; set; }

        public static Player StworzGracza(int nrGracza)     //Funkcja służąca do inicjowania obiektu typu gracz
        {
            Console.Clear();
            Player gracz = new Player();
            gracz.imie = Silnik.PobierzString($"Gracz nr {nrGracza}, podaj imię: ");
            gracz.symbol = Silnik.PobierzString($"Gracz nr {nrGracza}, podaj symbol: ");
            return gracz;  
        }
        
    }
}
