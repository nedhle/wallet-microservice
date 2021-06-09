package app.engine;


import app.model.Transaction;
import app.model.Wallet;
import app.repository.TransactionRepository;
import app.repository.WalletRepository;
import app.util.GenericUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
@Transactional
public class TransactionEngine {

    private static Logger logger = Logger.getLogger(String.valueOf(TransactionEngine.class));

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletRepository walletRepository;

    public List<Transaction> listAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Map<String, Object> param) throws Exception {

        Wallet wallet = walletRepository.getById(GenericUtil.uInt(param.get("wallet_id")));

        if(wallet==null)throw new Exception("Wallet is not found");

        if(GenericUtil.uBigDecimal(param.get("debitAmount")) == null)throw new Exception("Debit Amount is not be null");

        //check balance and debit
        if(wallet.getCurrentBalance().compareTo(GenericUtil.uBigDecimal(param.get("debitAmount"))) >= 0){
            Transaction tmpTransaction = new Transaction();
            tmpTransaction.setUnqTransactionId((String)param.get("unqTransactionId"));
            tmpTransaction.setDebitAmount(GenericUtil.uBigDecimal(param.get("debitAmount")));
            tmpTransaction.setWallet(wallet);

            Transaction transaction = transactionRepository.save(tmpTransaction);
            return transaction;
        }else{
            throw new Exception("Current balance is not big enough");
        }
    }
}
