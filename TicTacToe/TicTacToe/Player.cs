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

        public static Player StworzGracza(int nrGracza)
        {
            Player gracz = new Player();
            gracz.imie = Program.PobierzString($"Gracz nr {nrGracza}, podaj imię: ");
            gracz.symbol = Program.PobierzString($"Gracz nr {nrGracza}, podaj symbol: ");
            return gracz;  
        }
    }
}
