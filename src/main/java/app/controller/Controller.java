package app.controller;


import app.engine.TransactionEngine;
import app.engine.WalletEngine;
import app.model.Transaction;
import app.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class Controller {
    private static Logger logger = Logger.getLogger(String.valueOf(Controller.class));

    @Autowired
    WalletEngine walletEngine;

    @Autowired
    TransactionEngine transactionEngine;

    @GetMapping("/wallets")
    public ResponseEntity<List<Wallet>> hndListAllWallets(){
        logger.info("hndListAllWallets");
        try{
            List<Wallet> wallets = walletEngine.listAllWallets();
            if (wallets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            };
            return new ResponseEntity<>(wallets, HttpStatus.OK);
        }catch(Exception e){
            logger.warning("hndListAllWallets {" + e.getMessage() + "}");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> hndCreateWallet(@RequestBody Wallet tmpWallet){
        logger.info("hndCreateWallet");
        try {
            Wallet wallet = walletEngine.createWallet(tmpWallet);
            return new ResponseEntity<>(wallet, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.warning("hndCreateWallet {" + e.getMessage() + "}");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity<Object> hndCreateTransactions(@RequestBody Map<String, Object> param){
        logger.info("hndCreateTransactions");
        Map<String, Object> body = new LinkedHashMap<>();
        try {
            Transaction transaction = transactionEngine.createTransaction(param);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.warning("hndCreateTransactions {" + e.getMessage() + "}");
            body.put("timestamp", new Date());
            body.put("error",e.getMessage() );
            return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
