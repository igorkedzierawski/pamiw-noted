import { Component } from '@angular/core';
import { RequestEngine } from '../../mainsv/requestengine';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { NoteForm } from 'src/app/mainsv/data/noteform';
import { NoteEditor } from "../../noteeditor/noteeditor.component";
import { MainService } from 'src/app/mainsv/main.service';

@Component({
    selector: 'app-noteedit',
    standalone: true,
    templateUrl: './notecreate.component.html',
    styleUrls: ['./notecreate.component.scss'],
    imports: [
        MatButtonModule,
        CommonModule,
        NoteEditor
    ]
})
export class NotecreateComponent {
  
    constructor(
        protected readonly mainService: MainService,
        protected readonly reqeng: RequestEngine,
        protected readonly router: Router
    ) {}
  
    protected saveChanges(form: NoteForm) {
        this.reqeng.noteCreate(form).subscribe((note) => {
            this.router.navigate(['/note'], { queryParams: {id: note.id} });
        })
    }
  
    protected abandonChanges() {
        this.router.navigate(['/notes']);
    }

}
