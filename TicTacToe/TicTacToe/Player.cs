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

        public Player(int nrGracza)
        {
            Console.Clear();           
            imie = Silnik.PobierzString($"Gracz nr {nrGracza}, podaj imię: ");
            symbol = Silnik.PobierzString($"Gracz nr {nrGracza}, podaj symbol: ");    
        }
        
    }
}
