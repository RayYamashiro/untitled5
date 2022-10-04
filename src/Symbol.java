public class Symbol {

        private int symbolName;
        private volatile double probability;
        private volatile double amountOfInformation;
        private volatile int count;

        public Symbol(int symbolName) {
            this.symbolName = symbolName;
            probability = 0;
            amountOfInformation = 0;
            count = 0;
        }

         public Symbol(int symbolName, int count) {
            this.symbolName = symbolName;
            probability = 0;
            amountOfInformation = 0;
            this.count = count;
         }

        @Override
        public boolean equals(Object o)
        {
            Symbol that = (Symbol) o;
            if(this.getSymbolName() == that.getSymbolName())
                return true;
            else
                return false;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }

        public void setAmountOfTheInformation(double amountOfInformation) {
            this.amountOfInformation = amountOfInformation;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getSymbolName() {
            return symbolName;
        }

        public double getProbability() {
            return probability;
        }

        public double getAmountOfInformation() {
            return amountOfInformation;
        }

        public int getCount() {
            return count;
        }

        public void printInformationTaskByte()
        {
            System.out.println(String.format(
                    "* Byte - %-6s || Count - %-6d   || Probability - %-6f || Amount of information - %-6f * " , String.format("%02X", getSymbolName() & 0xFF), getCount(), getProbability(), getAmountOfInformation()
            ));
        }


    public void printInformationTaskUni()
    {
        System.out.println(String.format(
                "* Unicode - %-6s || Count - %-6d   || Probability - %-6f || Amount of information - %-15f * "  ,getSymbolName(), getCount(), getProbability(), getAmountOfInformation()
        ));
    }
}
