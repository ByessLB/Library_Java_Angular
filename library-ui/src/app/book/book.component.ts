import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { Category } from '../models/category';
import { Book } from '../models/book';
import { BookService } from '../services/book.service';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  public types = [ 'Isbn', 'Title'];
  public isbn: string;
  public title: string;
  public displayType: string = "Isbn";
  public headsTab = ['ISBN', 'TITLE', 'AUTHOR', 'RELEASE DATE', 'REGISTER DATE', 'TOTAL EXAMPLARIES', 'CATEGORY'];
  public isNoResult: boolean = true;
  public isFormSubmitted: boolean = false;
  public actionButton: string = 'save';
  public titleSaveOrUpdate: string = 'Add New Book Form';
  public messageModal: string;
  public displayMessageModal: boolean = false;

  public categories: Category[] = [{code:"", label:""}];
  public book = new Book();
  public searchBooksResult: Book[] = [];

  constructor(private bookService: BookService, private spinner:  NgxSpinnerService) { }

  ngOnInit(): void {
    /*
    charge les categories des books
    lors du chargement du component
    */
      this.loadCategories();
  }

  /*
  Methode pour charger les categories de book
  provenant du backend
  */
  loadCategories() {
    this.spinner.show();
    this.bookService.loadCategories().subscribe(
      (result: Category[]) => {
        this.spinner.hide();
        this.categories.push(...result);
      },
      (error) => {
        this.spinner.hide();
        console.error(error);
        this.buildMessageModal('An error occurs when retreiving categories data');
      }
    );
  }

  /**
  * Méthode qui permet d’enregistrer dans le serveur Backend,
  * une nouvelle donnée de livre récupérée à partir du formulaire
  * @param addBookForm
  */
  saveOrUpdateBook(addBookForm: NgForm) {
    this.displayMessageModal = false;
    if (!addBookForm.valid) {
      this.buildMessageModal('Error in the form. Please check the fields.');
      return;
    }
    this.setLocalDateToDatePicker(this.book);
    if (this.actionButton && this.actionButton == 'Save') {
      this.saveNewBook(this.book);
    } else if (this.actionButton && this.actionButton == 'Update') {
      this.updateBook(this.book);
    }
    this.titleSaveOrUpdate = 'Add New Book Form';
    this.actionButton = "Save";
  }

  /**
  * Enregistrer la date locale de la zone d’enregistrement de la propriété releaseDate du livre : 
  * Il existe un problème reconnu avec la conversion de datePicker @angular/Material Timezone.
  * @param book
  */
  setLocalDateToDatePicker(book: Book) {
    let localDate = new Date(book.releaseDate);
    if (localDate.getTimezoneOffset() < 0) {
      localDate.setMinutes(localDate.getMinutes() - localDate.getTimezoneOffset());
    } else {
      localDate.setMinutes(localDate.getMinutes() + localDate.getTimezoneOffset());
    }
    book.releaseDate = localDate;
  }

  /**
  * Sauvegarde d'un nouveau livre
  * @param book
  */
  saveNewBook(book: Book) {
    this.spinner.show();
    this.bookService.saveBook(book).subscribe(
      (result: Book) => {
        if (result.id) {
          this.spinner.hide();
          this.buildMessageModal('Save operation correctly done');
        }
      },
      error => {
        this.spinner.hide();
        this.buildMessageModal('Error during the save operation. Please try again later.');
      }
    );
  }

  /**
  * mis-à-jour d'un livre
  * @param book
  */
  updateBook(book: Book) {
    this.spinner.show();
    this.bookService.updateBook(book).subscribe(
      (result: Book) => {
        if (result.id) {
          this.updateResearchBooksTab(book);
          this.spinner.hide();
          this.buildMessageModal('Update operation correctly done');
        }
      },
      error => {
        this.spinner.hide();
        this.buildMessageModal('An erro occurs when updating the book data');
      }
    );
  }

  /**
  * Mettre à jour dans l’onglet liste,
  * le livre qui a été mise à jour
  * @param book
  */
  updateResearchBooksTab(book: Book) {
    if (this.searchBooksResult && this.searchBooksResult.length > 0) {
      let index : number = 0;
      this.searchBooksResult.forEach(element => {
        if (element.id == book.id) {
          this.searchBooksResult.splice(index, 1, book);
        }
        index++;
      });
      this.buildMessageModal('Book not found in the search result.');
    }
  }

  /**
  * Suppression d'un livre
  * @param book
  */
  deleteBook(book: Book) {
    this.spinner.show();
    this.displayMessageModal = false;
    this.bookService.deleteBook(book).subscribe(
      result => {
        for (let i = 0; i < this.searchBooksResult.length; i++) {
          if (this.searchBooksResult[i].id == book.id) {
            this.searchBooksResult.splice(i, 1);
          }
        }
        this.spinner.hide();
        this.buildMessageModal('Delete operation correctly done');
        if (this.searchBooksResult.length == 0) {
          this.isNoResult = true;
        }
      }
    );
  }

  /**
  * Définir le livre sélectionné comme livre à mettre à jour
  * @param book
  */
  setUpdateBook(book: Book) {
    this.titleSaveOrUpdate = 'Update Book Form';
    this.actionButton = 'Update';
    this.book = Object.assign({}, book);
    this.displayMessageModal = false;
  }

  /**
  * Effacer les données du formulaire NgForm
  * @param addBookForm
  */
  clearForm(addBookForm: NgForm) {
    addBookForm.form.reset();
    this.isbn = '';
    this.title = '';
    this.searchBooksResult = [];
    this.displayMessageModal = false;
  }

  /**
  * Recherche de livres selon title ou isbn
  * @param searchBookForm
  */
  searchBooksByType(searchBookForm) {
    this.spinner.show();
    this.displayMessageModal = false;
    if (!searchBookForm.valid) {
      this.buildMessageModal('Error in the form');
      return;
    }
    if (this.displayType === 'Isbn') {
      this.searchBooksResult = [];
      this.bookService.searchBookByIsbn(this.isbn).subscribe(
        (result: Book) => {
          if (result && result != null) {
            this.searchBooksResult.push(result);
            this.isNoResult = false;
            this.spinner.hide();
            return;
          }
          this.isNoResult = true;
          this.spinner.hide();
        },
        error => {
          this.spinner.hide();
          this.buildMessageModal('Error in the search');
        }
      );
    } else if (this.displayType == 'Title') {
      this.searchBooksResult = [];
      this.bookService.searchBookByTitle(this.title).subscribe(
        (result: Book[]) => {
          if (result && result != null) {
            this.searchBooksResult = result;
            this.isNoResult = false;
            this.spinner.hide();
            return;
          }
          this.isNoResult = true;
          this.spinner.hide();
        },
        error => {
          this.spinner.hide();
          this.buildMessageModal('Error in the search');
        }
      );
    }
    this.isFormSubmitted = searchBookForm.submitted;
  }

  /**
  * Construit le message à afficher suite à une action utilisateur
  * @param msg
  */
  buildMessageModal(msg: string) {
    this.messageModal = msg;
    this.displayMessageModal = true;
  }
}
