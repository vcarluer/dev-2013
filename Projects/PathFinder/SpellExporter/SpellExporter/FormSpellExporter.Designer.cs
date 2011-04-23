namespace SpellExporter
{
    partial class FormSpellExporter
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.splitContainer2 = new System.Windows.Forms.SplitContainer();
            this.btParse = new System.Windows.Forms.Button();
            this.txtToParse = new System.Windows.Forms.RichTextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.btSave = new System.Windows.Forms.Button();
            this.btIntegrate = new System.Windows.Forms.Button();
            this.label12 = new System.Windows.Forms.Label();
            this.txtDescription = new System.Windows.Forms.RichTextBox();
            this.txtName = new System.Windows.Forms.TextBox();
            this.label11 = new System.Windows.Forms.Label();
            this.txtSpellResistance = new System.Windows.Forms.TextBox();
            this.label10 = new System.Windows.Forms.Label();
            this.txtSavingThrow = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.txtDuration = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.txtTargetEffectArea = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.txtRange = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.txtComponents = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.txtCastingTime = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.txtScool = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.dgSpells = new System.Windows.Forms.DataGridView();
            this.label13 = new System.Windows.Forms.Label();
            this.txtRegister = new System.Windows.Forms.TextBox();
            this.label14 = new System.Windows.Forms.Label();
            this.txtBranch = new System.Windows.Forms.TextBox();
            this.txtLvlMagEns = new System.Windows.Forms.TextBox();
            this.label15 = new System.Windows.Forms.Label();
            this.txtLvlPriest = new System.Windows.Forms.TextBox();
            this.label16 = new System.Windows.Forms.Label();
            this.txtLvlPal = new System.Windows.Forms.TextBox();
            this.label17 = new System.Windows.Forms.Label();
            this.txtLvlBard = new System.Windows.Forms.TextBox();
            this.label18 = new System.Windows.Forms.Label();
            this.txtLvlDruid = new System.Windows.Forms.TextBox();
            this.label19 = new System.Windows.Forms.Label();
            this.txtLvlStriker = new System.Windows.Forms.TextBox();
            this.btParseList = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer2)).BeginInit();
            this.splitContainer2.Panel1.SuspendLayout();
            this.splitContainer2.Panel2.SuspendLayout();
            this.splitContainer2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgSpells)).BeginInit();
            this.SuspendLayout();
            // 
            // splitContainer1
            // 
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.splitContainer2);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.dgSpells);
            this.splitContainer1.Size = new System.Drawing.Size(1228, 709);
            this.splitContainer1.SplitterDistance = 716;
            this.splitContainer1.TabIndex = 0;
            // 
            // splitContainer2
            // 
            this.splitContainer2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer2.Location = new System.Drawing.Point(0, 0);
            this.splitContainer2.Name = "splitContainer2";
            this.splitContainer2.Orientation = System.Windows.Forms.Orientation.Horizontal;
            // 
            // splitContainer2.Panel1
            // 
            this.splitContainer2.Panel1.Controls.Add(this.btParseList);
            this.splitContainer2.Panel1.Controls.Add(this.btParse);
            this.splitContainer2.Panel1.Controls.Add(this.txtToParse);
            this.splitContainer2.Panel1.Controls.Add(this.label1);
            // 
            // splitContainer2.Panel2
            // 
            this.splitContainer2.Panel2.Controls.Add(this.txtLvlStriker);
            this.splitContainer2.Panel2.Controls.Add(this.label19);
            this.splitContainer2.Panel2.Controls.Add(this.txtLvlDruid);
            this.splitContainer2.Panel2.Controls.Add(this.label18);
            this.splitContainer2.Panel2.Controls.Add(this.txtLvlBard);
            this.splitContainer2.Panel2.Controls.Add(this.label17);
            this.splitContainer2.Panel2.Controls.Add(this.txtLvlPal);
            this.splitContainer2.Panel2.Controls.Add(this.label16);
            this.splitContainer2.Panel2.Controls.Add(this.txtLvlPriest);
            this.splitContainer2.Panel2.Controls.Add(this.label15);
            this.splitContainer2.Panel2.Controls.Add(this.txtLvlMagEns);
            this.splitContainer2.Panel2.Controls.Add(this.txtBranch);
            this.splitContainer2.Panel2.Controls.Add(this.label14);
            this.splitContainer2.Panel2.Controls.Add(this.txtRegister);
            this.splitContainer2.Panel2.Controls.Add(this.label13);
            this.splitContainer2.Panel2.Controls.Add(this.btSave);
            this.splitContainer2.Panel2.Controls.Add(this.btIntegrate);
            this.splitContainer2.Panel2.Controls.Add(this.label12);
            this.splitContainer2.Panel2.Controls.Add(this.txtDescription);
            this.splitContainer2.Panel2.Controls.Add(this.txtName);
            this.splitContainer2.Panel2.Controls.Add(this.label11);
            this.splitContainer2.Panel2.Controls.Add(this.txtSpellResistance);
            this.splitContainer2.Panel2.Controls.Add(this.label10);
            this.splitContainer2.Panel2.Controls.Add(this.txtSavingThrow);
            this.splitContainer2.Panel2.Controls.Add(this.label9);
            this.splitContainer2.Panel2.Controls.Add(this.txtDuration);
            this.splitContainer2.Panel2.Controls.Add(this.label8);
            this.splitContainer2.Panel2.Controls.Add(this.txtTargetEffectArea);
            this.splitContainer2.Panel2.Controls.Add(this.label7);
            this.splitContainer2.Panel2.Controls.Add(this.txtRange);
            this.splitContainer2.Panel2.Controls.Add(this.label6);
            this.splitContainer2.Panel2.Controls.Add(this.txtComponents);
            this.splitContainer2.Panel2.Controls.Add(this.label5);
            this.splitContainer2.Panel2.Controls.Add(this.txtCastingTime);
            this.splitContainer2.Panel2.Controls.Add(this.label4);
            this.splitContainer2.Panel2.Controls.Add(this.label3);
            this.splitContainer2.Panel2.Controls.Add(this.txtScool);
            this.splitContainer2.Panel2.Controls.Add(this.label2);
            this.splitContainer2.Size = new System.Drawing.Size(716, 709);
            this.splitContainer2.SplitterDistance = 387;
            this.splitContainer2.TabIndex = 0;
            // 
            // btParse
            // 
            this.btParse.Location = new System.Drawing.Point(7, 352);
            this.btParse.Name = "btParse";
            this.btParse.Size = new System.Drawing.Size(374, 32);
            this.btParse.TabIndex = 2;
            this.btParse.Text = "Parse !";
            this.btParse.UseVisualStyleBackColor = true;
            this.btParse.Click += new System.EventHandler(this.btParse_Click);
            // 
            // txtToParse
            // 
            this.txtToParse.Location = new System.Drawing.Point(7, 21);
            this.txtToParse.Name = "txtToParse";
            this.txtToParse.Size = new System.Drawing.Size(705, 324);
            this.txtToParse.TabIndex = 1;
            this.txtToParse.Text = "";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(4, 4);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(102, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Paste spell text here";
            // 
            // btSave
            // 
            this.btSave.Location = new System.Drawing.Point(388, 281);
            this.btSave.Name = "btSave";
            this.btSave.Size = new System.Drawing.Size(324, 26);
            this.btSave.TabIndex = 24;
            this.btSave.Text = "Save ALL";
            this.btSave.UseVisualStyleBackColor = true;
            // 
            // btIntegrate
            // 
            this.btIntegrate.Location = new System.Drawing.Point(388, 244);
            this.btIntegrate.Name = "btIntegrate";
            this.btIntegrate.Size = new System.Drawing.Size(324, 30);
            this.btIntegrate.TabIndex = 23;
            this.btIntegrate.Text = "Integrate =>";
            this.btIntegrate.UseVisualStyleBackColor = true;
            this.btIntegrate.Click += new System.EventHandler(this.btIntegrate_Click);
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(388, 9);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(63, 13);
            this.label12.TabIndex = 22;
            this.label12.Text = "Description:";
            // 
            // txtDescription
            // 
            this.txtDescription.Location = new System.Drawing.Point(388, 28);
            this.txtDescription.Name = "txtDescription";
            this.txtDescription.Size = new System.Drawing.Size(324, 210);
            this.txtDescription.TabIndex = 21;
            this.txtDescription.Text = "";
            // 
            // txtName
            // 
            this.txtName.Location = new System.Drawing.Point(61, 2);
            this.txtName.Name = "txtName";
            this.txtName.Size = new System.Drawing.Size(320, 20);
            this.txtName.TabIndex = 20;
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(12, 5);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(38, 13);
            this.label11.TabIndex = 19;
            this.label11.Text = "Name:";
            // 
            // txtSpellResistance
            // 
            this.txtSpellResistance.Location = new System.Drawing.Point(113, 263);
            this.txtSpellResistance.Name = "txtSpellResistance";
            this.txtSpellResistance.Size = new System.Drawing.Size(179, 20);
            this.txtSpellResistance.TabIndex = 18;
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(12, 266);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(89, 13);
            this.label10.TabIndex = 17;
            this.label10.Text = "Spell Resistance:";
            // 
            // txtSavingThrow
            // 
            this.txtSavingThrow.Location = new System.Drawing.Point(94, 237);
            this.txtSavingThrow.Name = "txtSavingThrow";
            this.txtSavingThrow.Size = new System.Drawing.Size(198, 20);
            this.txtSavingThrow.TabIndex = 16;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(12, 240);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(72, 13);
            this.label9.TabIndex = 15;
            this.label9.Text = "Saving throw:";
            // 
            // txtDuration
            // 
            this.txtDuration.Location = new System.Drawing.Point(85, 211);
            this.txtDuration.Name = "txtDuration";
            this.txtDuration.Size = new System.Drawing.Size(207, 20);
            this.txtDuration.TabIndex = 14;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(12, 214);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(50, 13);
            this.label8.TabIndex = 13;
            this.label8.Text = "Duration:";
            // 
            // txtTargetEffectArea
            // 
            this.txtTargetEffectArea.Location = new System.Drawing.Point(113, 185);
            this.txtTargetEffectArea.Name = "txtTargetEffectArea";
            this.txtTargetEffectArea.Size = new System.Drawing.Size(268, 20);
            this.txtTargetEffectArea.TabIndex = 12;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(12, 188);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(95, 13);
            this.label7.TabIndex = 11;
            this.label7.Text = "Target/Effet/Area:";
            // 
            // txtRange
            // 
            this.txtRange.Location = new System.Drawing.Point(86, 159);
            this.txtRange.Name = "txtRange";
            this.txtRange.Size = new System.Drawing.Size(206, 20);
            this.txtRange.TabIndex = 10;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(12, 162);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(42, 13);
            this.label6.TabIndex = 9;
            this.label6.Text = "Range:";
            // 
            // txtComponents
            // 
            this.txtComponents.Location = new System.Drawing.Point(85, 131);
            this.txtComponents.Name = "txtComponents";
            this.txtComponents.Size = new System.Drawing.Size(207, 20);
            this.txtComponents.TabIndex = 8;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(12, 134);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(69, 13);
            this.label5.TabIndex = 7;
            this.label5.Text = "Components:";
            // 
            // txtCastingTime
            // 
            this.txtCastingTime.Location = new System.Drawing.Point(85, 105);
            this.txtCastingTime.Name = "txtCastingTime";
            this.txtCastingTime.Size = new System.Drawing.Size(100, 20);
            this.txtCastingTime.TabIndex = 6;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(12, 108);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(67, 13);
            this.label4.TabIndex = 5;
            this.label4.Text = "Casting time:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(192, 57);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(54, 13);
            this.label3.TabIndex = 3;
            this.label3.Text = "Mag/Ens:";
            // 
            // txtScool
            // 
            this.txtScool.Location = new System.Drawing.Point(61, 28);
            this.txtScool.Name = "txtScool";
            this.txtScool.Size = new System.Drawing.Size(125, 20);
            this.txtScool.TabIndex = 1;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 32);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(43, 13);
            this.label2.TabIndex = 0;
            this.label2.Text = "School:";
            // 
            // dgSpells
            // 
            this.dgSpells.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgSpells.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgSpells.Location = new System.Drawing.Point(0, 0);
            this.dgSpells.Name = "dgSpells";
            this.dgSpells.Size = new System.Drawing.Size(508, 709);
            this.dgSpells.TabIndex = 0;
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(192, 31);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(49, 13);
            this.label13.TabIndex = 25;
            this.label13.Text = "Register:";
            // 
            // txtRegister
            // 
            this.txtRegister.Location = new System.Drawing.Point(247, 29);
            this.txtRegister.Name = "txtRegister";
            this.txtRegister.Size = new System.Drawing.Size(134, 20);
            this.txtRegister.TabIndex = 26;
            // 
            // label14
            // 
            this.label14.AutoSize = true;
            this.label14.Location = new System.Drawing.Point(12, 56);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(44, 13);
            this.label14.TabIndex = 27;
            this.label14.Text = "Branch:";
            // 
            // txtBranch
            // 
            this.txtBranch.Location = new System.Drawing.Point(61, 54);
            this.txtBranch.Name = "txtBranch";
            this.txtBranch.Size = new System.Drawing.Size(124, 20);
            this.txtBranch.TabIndex = 28;
            // 
            // txtLvlMagEns
            // 
            this.txtLvlMagEns.Location = new System.Drawing.Point(252, 53);
            this.txtLvlMagEns.Name = "txtLvlMagEns";
            this.txtLvlMagEns.Size = new System.Drawing.Size(29, 20);
            this.txtLvlMagEns.TabIndex = 29;
            // 
            // label15
            // 
            this.label15.AutoSize = true;
            this.label15.Location = new System.Drawing.Point(288, 57);
            this.label15.Name = "label15";
            this.label15.Size = new System.Drawing.Size(22, 13);
            this.label15.TabIndex = 30;
            this.label15.Text = "Pri:";
            // 
            // txtLvlPriest
            // 
            this.txtLvlPriest.Location = new System.Drawing.Point(316, 53);
            this.txtLvlPriest.Name = "txtLvlPriest";
            this.txtLvlPriest.Size = new System.Drawing.Size(28, 20);
            this.txtLvlPriest.TabIndex = 31;
            // 
            // label16
            // 
            this.label16.AutoSize = true;
            this.label16.Location = new System.Drawing.Point(12, 83);
            this.label16.Name = "label16";
            this.label16.Size = new System.Drawing.Size(25, 13);
            this.label16.TabIndex = 32;
            this.label16.Text = "Pal:";
            // 
            // txtLvlPal
            // 
            this.txtLvlPal.Location = new System.Drawing.Point(61, 80);
            this.txtLvlPal.Name = "txtLvlPal";
            this.txtLvlPal.Size = new System.Drawing.Size(23, 20);
            this.txtLvlPal.TabIndex = 33;
            // 
            // label17
            // 
            this.label17.AutoSize = true;
            this.label17.Location = new System.Drawing.Point(91, 83);
            this.label17.Name = "label17";
            this.label17.Size = new System.Drawing.Size(32, 13);
            this.label17.TabIndex = 34;
            this.label17.Text = "Bard:";
            // 
            // txtLvlBard
            // 
            this.txtLvlBard.Location = new System.Drawing.Point(129, 80);
            this.txtLvlBard.Name = "txtLvlBard";
            this.txtLvlBard.Size = new System.Drawing.Size(23, 20);
            this.txtLvlBard.TabIndex = 35;
            // 
            // label18
            // 
            this.label18.AutoSize = true;
            this.label18.Location = new System.Drawing.Point(168, 83);
            this.label18.Name = "label18";
            this.label18.Size = new System.Drawing.Size(35, 13);
            this.label18.TabIndex = 36;
            this.label18.Text = "Druid:";
            // 
            // txtLvlDruid
            // 
            this.txtLvlDruid.Location = new System.Drawing.Point(209, 80);
            this.txtLvlDruid.Name = "txtLvlDruid";
            this.txtLvlDruid.Size = new System.Drawing.Size(23, 20);
            this.txtLvlDruid.TabIndex = 37;
            // 
            // label19
            // 
            this.label19.AutoSize = true;
            this.label19.Location = new System.Drawing.Point(240, 83);
            this.label19.Name = "label19";
            this.label19.Size = new System.Drawing.Size(31, 13);
            this.label19.TabIndex = 38;
            this.label19.Text = "Strik:";
            // 
            // txtLvlStriker
            // 
            this.txtLvlStriker.Location = new System.Drawing.Point(277, 80);
            this.txtLvlStriker.Name = "txtLvlStriker";
            this.txtLvlStriker.Size = new System.Drawing.Size(25, 20);
            this.txtLvlStriker.TabIndex = 39;
            // 
            // btParseList
            // 
            this.btParseList.Location = new System.Drawing.Point(391, 352);
            this.btParseList.Name = "btParseList";
            this.btParseList.Size = new System.Drawing.Size(321, 32);
            this.btParseList.TabIndex = 3;
            this.btParseList.Text = "Parse List !";
            this.btParseList.UseVisualStyleBackColor = true;
            this.btParseList.Click += new System.EventHandler(this.btParseList_Click);
            // 
            // FormSpellExporter
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1228, 709);
            this.Controls.Add(this.splitContainer1);
            this.Name = "FormSpellExporter";
            this.Text = "Spell exporter";
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.splitContainer2.Panel1.ResumeLayout(false);
            this.splitContainer2.Panel1.PerformLayout();
            this.splitContainer2.Panel2.ResumeLayout(false);
            this.splitContainer2.Panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer2)).EndInit();
            this.splitContainer2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgSpells)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.SplitContainer splitContainer2;
        private System.Windows.Forms.RichTextBox txtToParse;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button btParse;
        private System.Windows.Forms.Button btIntegrate;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.RichTextBox txtDescription;
        private System.Windows.Forms.TextBox txtName;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.TextBox txtSpellResistance;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.TextBox txtSavingThrow;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.TextBox txtDuration;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.TextBox txtTargetEffectArea;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox txtRange;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox txtComponents;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox txtCastingTime;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox txtScool;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.DataGridView dgSpells;
        private System.Windows.Forms.Button btSave;
        private System.Windows.Forms.TextBox txtRegister;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.TextBox txtBranch;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.TextBox txtLvlMagEns;
        private System.Windows.Forms.TextBox txtLvlPriest;
        private System.Windows.Forms.Label label15;
        private System.Windows.Forms.TextBox txtLvlPal;
        private System.Windows.Forms.Label label16;
        private System.Windows.Forms.TextBox txtLvlBard;
        private System.Windows.Forms.Label label17;
        private System.Windows.Forms.TextBox txtLvlDruid;
        private System.Windows.Forms.Label label18;
        private System.Windows.Forms.TextBox txtLvlStriker;
        private System.Windows.Forms.Label label19;
        private System.Windows.Forms.Button btParseList;
    }
}

