import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { NotepreviewComponent } from "./notepreview/notepreview.component";
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule } from '@angular/material/paginator'; 
import { PaginatorComponent } from './paginator/paginator.component';
import { MainService } from '../mainsv/main.service';
import { MatButtonModule } from '@angular/material/button';
import { Note } from '../mainsv/data/note';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { RequestEngine } from '../mainsv/requestengine';
import { None, Option } from '../option/option.module';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
    selector: 'notelist',
    templateUrl: './notelist.component.html',
    styleUrls: ['./notelist.component.scss'],
    standalone: true,
    imports: [
        NotepreviewComponent,
        MatListModule, 
        MatButtonModule,
        MatPaginatorModule,
        PaginatorComponent,
        MatProgressSpinnerModule,
        CommonModule
    ]
})

export class NotelistComponent {

    protected readonly DEFAULT_PAGE: number = 1;
    protected readonly DEFAULT_PAGE_SIZE: number = 5;

    protected page: number = this.DEFAULT_PAGE;
    protected maxPage: number = this.DEFAULT_PAGE;
    protected size: number = this.DEFAULT_PAGE_SIZE;

    protected notesLoaded: boolean = false;
    protected notes: Note[] = [];

    @ViewChild(PaginatorComponent) paginator!: PaginatorComponent;
    
    constructor(
        protected readonly mainService: MainService,
        protected readonly reqeng: RequestEngine,
        protected readonly route: ActivatedRoute,
        protected readonly router: Router
    ) {}

    ngOnInit() {
        this.route.queryParamMap.subscribe(params => {
            this.processQueryParameterChanges(params);
        });
    }

    ngAfterViewChecked() {
        this.paginator.gotoPageEvent.subscribe(page => {
            this.gotoPage(page);
        });
    }

    private processQueryParameterChanges(params: ParamMap) {
        let previousPage = this.page;
        let previousSize = this.size;
        let page = parseInt(params.get('page')+"", 10);
        if(!Number.isInteger(page) || page <= 0) {
            page = this.DEFAULT_PAGE;
        }
        this.page = page;

        let size = parseInt(params.get('size')+"", 10);
        if(!Number.isInteger(size) || size <= 0) {
            size = this.DEFAULT_PAGE_SIZE;
        }
        this.size = size;

        if(this.page == previousPage && this.size == previousSize && this.notes[0] != undefined) {
            return;
        }

        this.reloadListState();
    }

    private reloadListState() {
        this.reqeng.notePage(this.page-1, this.size).subscribe(page => {
            this.notes = page.content;
            this.notesLoaded = true;
            this.maxPage = page.totalPages;
        });
    }

    protected gotoPage(page: number) {
        let currentParams = { ...this.route.snapshot.queryParams };
        currentParams['page'] = page
        this.router.navigate(['/notes'], { queryParams: currentParams });
    }

    protected gotoNote(id: number) {
        this.router.navigate(['/note'], { queryParams: {id: id} });
    }

}
