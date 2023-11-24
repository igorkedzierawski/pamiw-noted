import { Component, ViewChild, EventEmitter, Output, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';

@Component({
    selector: 'paginator',
    templateUrl: './paginator.component.html',
    styleUrls: ['./paginator.component.scss'],
    standalone: true,
    imports: [
        MatButtonModule
    ]
})
export class PaginatorComponent {

    private _currentPage: number = 1;
    @Input()
    set currentPage(page: number) {
        this._currentPage = page;
    }
    get currentPage(): number {
        return this._currentPage;
    }

    @Input() maxPage: number = 1;

    @Output() gotoPageEvent = new EventEmitter<number>();

    @ViewChild('pageinput') pageinput!: any;

    protected isThisFirstPage(): boolean {
        return this._currentPage <= 1;
    }

    protected isThisLastPage(): boolean {
        return this.currentPage >= this.maxPage;
    }

    protected gotoPage(page: number) {
        if(this.currentPage == page) {
            return;
        }
        this.currentPage = page;
        this.gotoPageEvent.emit(page);
    }

    protected gotoFirstPage() {
        this.gotoPage(1);
    }

    protected gotoPreviousPage() {
        this.gotoPage(Math.max(1, this.currentPage - 1));
    }

    protected gotoNextPage() {
        this.gotoPage(Math.min(this.maxPage, this.currentPage + 1));
    }

    protected gotoLastPage() {
        this.gotoPage(this.maxPage);
    }

    protected gotoArbitraryPage() {
        let page = this.pageinput.nativeElement.value;
        if(1 <= page && page <= this.maxPage) {
            this.gotoPage(page);
        }
    }

}
