import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";


@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

    title = "Loibrary Administration";

    constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
    }

    gotoBookPage() {
        this.router.navigateByUrl('/book-page');
    }

    gotoCustomerPage() {
        this.router.navigateByUrl('/customer-page');
    }

    gotoLoanPage() {
        this.router.navigateByUrl('/loan-page');
    }
}