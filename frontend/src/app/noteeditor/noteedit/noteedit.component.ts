import { Component } from '@angular/core';
import { MainService } from '../../mainsv/main.service';
import { RequestEngine } from '../../mainsv/requestengine';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Note } from '../../mainsv/data/note';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { NoteEditor } from "../../noteeditor/noteeditor.component";
import { NoteForm } from '../../mainsv/data/noteform';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { None, Option, OptionModule, isNone } from "../../option/option.module";

@Component({
    selector: 'noteedit',
    standalone: true,
    templateUrl: './noteedit.component.html',
    styleUrls: ['./noteedit.component.scss'],
    imports: [
        MatButtonModule,
        CommonModule,
        MatProgressSpinnerModule,
        NoteEditor,
        OptionModule
    ]
})
export class NoteeditComponent {

    private id: Option<number>;
  
    protected note: Option<Note>;
  
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
  
    private processQueryParameterChanges(params: ParamMap) {
        let previousId = this.id;
        let id = parseInt(params.get('id')+"", 10);
        if(!Number.isInteger(id)) {
            return;
        }
        this.id = id;
        if(this.id == previousId) {
            return;
        }
  
        this.reloadViewState();
    }
  
    private reloadViewState() {
        if(isNone(this.id)) {
            this.note = None;
            return;
        }
        this.reqeng.noteGet(this.id).subscribe(note => {
            this.note = note;
        });
    }
  
    protected saveChanges(form: NoteForm) {
        this.reqeng.noteUpdate(this.id!, form).subscribe((note) => {
            this.router.navigate(['/note'], { queryParams: {id: note.id} });
        })
    }
  
    protected abandonChanges() {
        this.router.navigate(['/note'], { queryParams: {id: this.id} });
    }

}
