using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SpellExporter
{
    public class Spell
    {
        public static string ExportSep = "_||_";
        public static string ExportNL = "_NL_";

        public string Name { get; set; }
        public string School { get; set; }
        public string Register { get; set; }
        public string Branch { get; set; }
        public string LevelMagician { get; set; }
        public string LevelPriest { get; set; }
        public string LevelPaladin { get; set; }
        public string LevelBard { get; set; }
        public string LevelDruid { get; set; }
        public string LevelStriker { get; set; }

        public string CastingTime { get; set; }
        public string Components { get; set; }
        public string Range { get; set; }
        public string TargetEffectArea { get; set; }
        public string Duration { get; set; }
        public string SavingThrow { get; set; }
        public string SpellResistance { get; set; }
        public string Description { get; set; }

        private string currentExport;

        public string SerializeForExport()
        {
            this.currentExport = String.Empty;
            Append(this.Name);
            Append(this.School);
            Append(this.Register);
            Append(this.Branch);
            Append(this.LevelMagician);
            Append(this.LevelPriest);
            Append(this.LevelPaladin);
            Append(this.LevelBard);
            Append(this.LevelDruid);
            Append(this.LevelStriker);
            Append(this.CastingTime);
            Append(this.Components);
            Append(this.Range);
            Append(this.TargetEffectArea);
            Append(this.Duration);
            Append(this.SavingThrow);
            Append(this.SpellResistance);
            Append(this.Description);

            this.currentExport = this.currentExport.Replace(SpellExpoterService.Nl, ExportNL);

            return this.currentExport;
        }

        private void Append(string str)
        {
            this.currentExport += str + ExportSep;
        }
    }
}
