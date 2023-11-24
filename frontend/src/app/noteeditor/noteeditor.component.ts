import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { NoteForm } from '../mainsv/data/noteform';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
    selector: 'noteeditor',
    standalone: true,
    templateUrl: './noteeditor.component.html',
    styleUrls: ['./noteeditor.component.scss'],
    imports: [
        MatButtonModule,
        CommonModule,
        MatInputModule,
        MatFormFieldModule,
        FormsModule
    ]
})
export class NoteEditor {

    @Input() title: string = "";
    @Input() content: string = "";

    @Output() saveChanges: EventEmitter<NoteForm> = new EventEmitter();
    @Output() abandonChanges: EventEmitter<void> = new EventEmitter();
  
    constructor() {}
  
    protected saveChanges0() {
        this.saveChanges.emit({
            title: this.title,
            content: this.content
        });
    }
  
    protected abandonChanges0() {
        this.abandonChanges.emit();
    }
  
}
