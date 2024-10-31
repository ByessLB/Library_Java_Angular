package learn.byesslb.library.loan;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("loanService")
@Transactional
public class LoanServiceImpl implements ILoanService {

    @Autowired
    private ILoanDao loanDao;

    @Override
    public List<Loan> findAllLoansByEndDateBefore(LocalDate maxEnDate) {
        return loanDao.findByEndDateBefore(maxEnDate);
    }

    @Override
    public List<Loan> getAllOpenLoansOfThisCustomer(String email, LoanStatus status) {
        return loanDao.getAllOpenLoansOfThisCustomer(email, status);
    }

    @Override
    public Loan getOpenedLoan(SimpleLoanDTO simpleLoanDTO) {
        return loanDao.getLoanByCriteria(simpleLoanDTO.getBookId(), simpleLoanDTO.getCustomerId(), LoanStatus.OPEN);
    }

    @Override
    public boolean checkIfLoanExists(SimpleLoanDTO simpleLoanDTO) {
        Loan loan = loanDao.getLoanByCriteria(simpleLoanDTO.getBookId(), simpleLoanDTO.getCustomerId(), LoanStatus.OPEN);
        if (loan != null) {
            return true;
        }
        return false;
    }

    @Override
    public Loan saveLoan(Loan loan) {
        return loanDao.save(loan);
    }

    // On fera de la suppression logique car le status de l'objet Loan est positionné à CLOSE
    @Override
    public void closeLoan(Loan loan) {
        loanDao.save(loan);
    }
}
