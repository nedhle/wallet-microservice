package app.engine;


import app.model.Wallet;
import app.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WalletEngine {
    @Autowired
    WalletRepository walletRepository;

    public List<Wallet> listAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet createWallet(Wallet tmpWallet) {
        return walletRepository.save(tmpWallet);
    }
}
