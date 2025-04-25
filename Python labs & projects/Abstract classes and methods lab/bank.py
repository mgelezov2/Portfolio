from abc import ABC, abstractmethod

class Bank(ABC):

    def basicinfo(self):

        print("This is a generic bank")

        return "Generic bank: 0"

    @abstractmethod

    def withdraw(self):

        pass


class Swiss(Bank):

    def __init__(self):

        self.bal = 1000
    
    def basicinfo(self):
        # ATTEMPT 1 (Half successful)
        
        # print("This is the Swiss Bank")

        # s = "Swiss Bank :" + str(self.bal)

        # return str(s)
        #===========================================

        print("This is the Swiss Bank: ")

        s = f"Swiss Bank: {self.bal}"

        return str(s)

    def withdraw(self, amount):

        if(amount > self.bal):

            print("Insufficient funds")
        
        else:

            self.bal = self.bal - amount

            print("Withdrawn amount: " + str(amount))

        print("New balance :" + str(self.bal))

        return self.bal
    
def main():
    assert issubclass(Bank, ABC), "Bank must derive from class ABC"
    s = Swiss()
    print(s.basicinfo())
    s.withdraw(30)
    s.withdraw(1000)

if __name__ == "__main__":
    main()
