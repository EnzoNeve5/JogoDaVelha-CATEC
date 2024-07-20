import tkinter as tk
from tkinter import messagebox

class JogoDaVelhaGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Jogo da Velha")
        
        self.current_player = 'X'
        self.board = [['' for _ in range(3)] for _ in range(3)]
        self.buttons = [[None for _ in range(3)] for _ in range(3)]
        
        self.create_board_buttons()
    
    def create_board_buttons(self):
        for i in range(3):
            for j in range(3):
                button = tk.Button(self.root, text='', font=('Arial', 20), width=6, height=3,
                                   command=lambda row=i, col=j: self.on_button_click(row, col))
                button.grid(row=i, column=j, padx=5, pady=5)
                self.buttons[i][j] = button
    
    def on_button_click(self, row, col):
        if self.board[row][col] == '':
            self.board[row][col] = self.current_player
            self.buttons[row][col].configure(text=self.current_player)
            
            if self.check_winner(row, col):
                messagebox.showinfo("Fim de jogo", f"Jogador {self.current_player} venceu!")
                self.reset_game()
            elif self.check_draw():
                messagebox.showinfo("Fim de jogo", "O jogo terminou em empate!")
                self.reset_game()
            else:
                self.current_player = 'O' if self.current_player == 'X' else 'X'
    
    def check_winner(self, row, col):
        
        if self.board[row][0] == self.board[row][1] == self.board[row][2] != '':
            return True
        
        if self.board[0][col] == self.board[1][col] == self.board[2][col] != '':
            return True
        
        if (self.board[0][0] == self.board[1][1] == self.board[2][2] != '' or
            self.board[0][2] == self.board[1][1] == self.board[2][0] != ''):
            return True
        return False
    
    def check_draw(self):
        for row in self.board:
            for cell in row:
                if cell == '':
                    return False
        return True
    
    def reset_game(self):
        self.current_player = 'X'
        self.board = [['' for _ in range(3)] for _ in range(3)]
        
        for i in range(3):
            for j in range(3):
                self.buttons[i][j].configure(text='')

if __name__ == "__main__":
    root = tk.Tk()
    jogo_da_velha_gui = JogoDaVelhaGUI(root)
    root.mainloop()
